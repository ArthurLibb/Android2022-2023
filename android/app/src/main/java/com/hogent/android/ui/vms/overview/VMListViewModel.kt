package com.hogent.android.ui.vms.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hogent.android.data.entities.*
import com.hogent.android.data.repositories.VmOverviewRepository
import com.hogent.android.network.dtos.response.ProjectResponse
import com.hogent.android.network.dtos.response.VirtualmachineIndex
import com.hogent.android.util.AuthenticationManager
import kotlinx.coroutines.runBlocking
import timber.log.Timber


class VMListViewModel(val repo: VmOverviewRepository) : ViewModel() {

    private val _projecten = MutableLiveData<ProjectResponse>()
    private var _virtualmachine = MutableLiveData<List<VirtualmachineIndex>>()


    val projecten: LiveData<ProjectResponse>
        get() = _projecten;

    val virtualmachine: LiveData<List<VirtualmachineIndex>>
        get() = _virtualmachine;

    init {
        runBlocking {
            refreshProjects();
        }
    }

    suspend fun refreshProjects() {
        val customerId = AuthenticationManager.getKlantId()!!
        val virtualMachineList = mutableListOf<VirtualmachineIndex>()

        _projecten.value = repo.getByCustomerId(customerId)
        Timber.d(
            String.format(
                "Landed on vmlist viewmodel page, this user has %d projects",
                projecten.value?.projects?.size ?: 0
            )
        )
        Timber.wtf(_projecten.value.toString())

        if(_projecten.value == null || _projecten.value!!.projects.isEmpty()){
            _projecten.postValue(ProjectResponse( listOf(Project(-1, "Geen projecten", null))))
            return
        }

        _projecten.value?.projects?.forEach { project ->
            val projectVMs = project.virtualMachines

            if (projectVMs != null) {
                virtualMachineList.addAll(projectVMs)
            }
        }
        _virtualmachine.value = virtualMachineList
    }
}


