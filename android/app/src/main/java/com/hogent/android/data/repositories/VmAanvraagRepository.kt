package com.hogent.android.data.repositories

import com.hogent.android.data.entities.*
import com.hogent.android.network.dtos.request.CreateProjectDto
import com.hogent.android.network.dtos.response.DetailsVirtualMachine
import com.hogent.android.network.dtos.response.ProjectResponse
import com.hogent.android.network.dtos.response.ProjectResponseAfterCreate
import com.hogent.android.network.services.ContractApi
import com.hogent.android.network.services.ProjectApi
import com.hogent.android.network.services.VirtualMachineApi
import com.hogent.android.ui.components.forms.RequestForm
import com.hogent.android.util.AuthenticationManager
import retrofit2.Response
import timber.log.Timber
import java.util.*


class VmAanvraagRepository() {

    private val customerId = AuthenticationManager.getKlantId()!!

    private val contractApi = ContractApi.retrofitService;
    private val vmApi = VirtualMachineApi.retrofitService;
    private val projectApi = ProjectApi.retrofitService;


    suspend fun create(form: RequestForm){
        /*val hardware = HardWare(form.memory!!, form.storage!!, form.cpuCoresValue!!)
        val backup = Backup(form.backUpType, null)

        /*Dto maken*/
        val startDate = form.startDate!!
        val endDate = form.endDate!!
        val dto = ContractDto(startDate, endDate)
        val contract: Contract = contractApi.createContract(dto)

        /*vm maken*/
        val vm = DetailsVirtualMachine(name = form.naamVm!!, mode = form.modeVm!!, hardware = hardware, backup = backup, operatingSystem = form.os!!, contractId = contract.id, projectId = form.project_id!!)
        Timber.d(vm.toString())

        vmApi.createVM(vm)*/
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


}