package com.hogent.android.data.repositories

import com.hogent.android.data.entities.*
import com.hogent.android.network.dtos.request.BackUpDtoCreateVm
import com.hogent.android.network.dtos.request.CreateProjectDto
import com.hogent.android.network.dtos.request.VirtualMachineCreateRequest
import com.hogent.android.network.dtos.request.VirtualMachineDtoCreate
import com.hogent.android.network.dtos.response.*
import com.hogent.android.network.services.ProjectApi
import com.hogent.android.network.services.VirtualMachineApi
import com.hogent.android.ui.components.forms.RequestForm
import com.hogent.android.util.AuthenticationManager
import retrofit2.Response
import timber.log.Timber
import java.util.*


class VmAanvraagRepository() {

    private val customerId = AuthenticationManager.getKlantId()!!

    private val vmApi = VirtualMachineApi.retrofitService;
    private val projectApi = ProjectApi.retrofitService;


    suspend fun create(form: RequestForm) : VirtualMachineReponseCreate? {
        val hardware = HardWare(form.memory!!, form.storage!!, form.cpuCoresValue!!)
        val backup = BackUpDtoCreateVm(form.backUpType, null)

        /*vm maken*/
        val vm = VirtualMachineDtoCreate(name = form.naamVm!!, hardware =  hardware, operatingSystem = form.os!!
                                        , backUp = backup, projectId = form.project_id!!, start = form.startDate!!, end= form.endDate!! )
        Timber.d(vm.toString())
        val requestObj = VirtualMachineCreateRequest(virtualMachine = vm, customerId)
        val reponse = vmApi.createVM(requestObj)
        if(reponse.isSuccessful){
            return reponse.body()
        }
        return null
    }

    suspend fun getProjecten(): ProjectResponse? {
        val reponse = projectApi.getByCustomerId(customerId)
        if(reponse.isSuccessful){
            return reponse.body()
        }
        return null
    }

    suspend fun  createProject(name : String): ProjectResponseAfterCreate?{
        val proj = CreateProjectDto(name = name, klantId = customerId)
        val response = projectApi.createProject(proj)
        if(response.isSuccessful){
            return response.body()
        }
        return  null
    }

    suspend fun getVmsByProjectId(projectId : Int): VirtualMachineReponse?{
        val reponse = vmApi.getByProjectId(projectId);
        if(reponse.isSuccessful){
            return reponse.body()
        }
        return null
    }

}