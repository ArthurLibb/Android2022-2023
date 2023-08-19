package com.hogent.android.data.repositories


import com.hogent.android.network.dtos.response.ProjectResponse
import com.hogent.android.network.services.ProjectApi
import com.hogent.android.network.services.VirtualMachineApi
import timber.log.Timber

class VmOverviewRepository {

    private val vmApi = VirtualMachineApi.retrofitService;
    private val projectApi = ProjectApi.retrofitService;

    suspend fun getByCustomerId(id: Int): ProjectResponse?{
        Timber.d("Retrieving projects with id: %d", id)
        val reponse = projectApi.getByCustomerId(id)
        if(reponse.isSuccessful){
            return reponse.body()
        }
        return null
    }

}