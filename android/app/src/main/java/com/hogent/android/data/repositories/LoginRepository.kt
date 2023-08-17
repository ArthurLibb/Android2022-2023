package com.hogent.android.data.repositories

import com.hogent.android.network.dtos.request.LoginCredentials
import com.hogent.android.network.dtos.response.LoginReponse
import com.hogent.android.network.services.LoginApi.loginApi
import timber.log.Timber

class LoginRepository {
    suspend fun login(email : String, password : String): LoginReponse?{
        val reponse = loginApi.loginKlant(LoginCredentials(email, password))
        Timber.log(reponse)
        if(reponse.body()){

        }
    }
}