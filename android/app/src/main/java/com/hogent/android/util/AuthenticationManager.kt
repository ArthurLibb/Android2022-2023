package com.hogent.android.util

import androidx.lifecycle.MutableLiveData
import com.hogent.android.data.entities.Customer
import com.hogent.android.network.services.CustomerApi.customerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class AuthenticationManager() {

    val IdKlant = MutableLiveData<Int>()
    val klant = MutableLiveData<Customer?>()
    var authenticationState = MutableLiveData(AuthenticationState.UNAUTHENTICATED)

    companion object {
        @Volatile
        private lateinit var instance: AuthenticationManager

        fun getInstance(): AuthenticationManager {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = AuthenticationManager()
                }
                return instance;
            }
        }

        fun getKlantId() : Int?{
            return instance.IdKlant.value
        }
        fun getCustomer(): Customer?{
            return instance.klant.value
        }
        fun loggedIn(): Boolean{
            return instance.loggedIn()
        }

        suspend fun setCustomer(id: Int){
            if (!::instance.isInitialized) {
                throw IllegalArgumentException("Error in Authenticationmanager.")
            }else{
                instance.IdKlant.postValue(id)
                val klant: Customer? = fetchKlantById(id)
                if(klant != null){
                    instance.klant.postValue(klant)
                    instance.authenticationState.postValue(AuthenticationState.AUTHENTICATED)
                }
            }
        }

        private suspend fun fetchKlantById(id :Int): Customer? {
            return try{
                withContext(Dispatchers.IO){
                    val response = customerService.getCustomer(id)
                    Timber.d("Getting customer")
                    if(response.isSuccessful){
                        Timber.d(response.body().toString())
                         response.body()
                    }
                    else{
                        null
                    }
                }
            }catch (e :Exception){
                Timber.e("Faild to get klant by Id");
                null
            }
        }
    }

    fun loggedIn(): Boolean{
        return authenticationState.value == AuthenticationState.AUTHENTICATED
    }
    fun logOut() {
        klant.postValue(null);
        authenticationState.postValue(AuthenticationState.UNAUTHENTICATED);
    }

}



enum class AuthenticationState {
    AUTHENTICATED, UNAUTHENTICATED
}