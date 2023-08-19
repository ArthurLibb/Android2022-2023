
package com.hogent.android.ui.vms.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hogent.android.data.entities.Backup
import com.hogent.android.data.entities.Contract
import com.hogent.android.data.repositories.VmDetailRepository
import com.hogent.android.network.dtos.response.DetailsVirtualMachine
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class VMDetailsViewModel(val repo : VmDetailRepository) : ViewModel() {

    private val _vm = MutableLiveData<DetailsVirtualMachine>();
    private val _navBack = MutableLiveData(false);
    private val _backUpNotNullValue = MutableLiveData<String>();

    val vm : LiveData<DetailsVirtualMachine>
        get() = _vm

    val backUpNotNullValue : LiveData<String>
        get() = _backUpNotNullValue

    val navBack : LiveData<Boolean>
        get() = _navBack


    init {
        runBlocking {
            var reponse = repo.getVirtualMachine()
            _vm.value = reponse
            if(reponse?.backUp?.lastBackUp == null){
                _backUpNotNullValue.value = "Nog geen"
            }else{
                _backUpNotNullValue.value = ""
            }
        }
    }



    fun navigateBack(){
        _navBack.postValue(true);

    }
}