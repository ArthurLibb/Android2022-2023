package com.hogent.android.data.repositories

import com.hogent.android.data.entities.Contract
import com.hogent.android.network.dtos.response.DetailsVirtualMachine
import com.hogent.android.network.services.ContractApi
import com.hogent.android.network.services.VirtualMachineApi
import timber.log.Timber

class VmDetailRepository(val vm_id : Int) {

    private val contractApi = ContractApi.retrofitService;
    private val vmApi = VirtualMachineApi.retrofitService;


    suspend fun getVirtualMachine(): DetailsVirtualMachine?{
        Timber.d("Requesting VM information with ID: %d", vm_id)
        val response = vmApi.getById(vm_id);
        if(response.isSuccessful && response.body() != null){
            Timber.wtf(response.body().toString())
            return response.body()
        }
        return null;

    }

}
