package com.hogent.android.data.repositories

import com.hogent.android.network.dtos.request.LoginCredentials
import com.hogent.android.network.dtos.request.RegisterKlant
import com.hogent.android.network.dtos.response.LoginReponse
import com.hogent.android.network.services.LoginApi.loginApi
import com.hogent.android.ui.components.forms.RegisterForm
import com.hogent.android.util.AuthenticationManager
import com.hogent.android.util.TimberUtils
import timber.log.Timber

class LoginRepository {
    suspend fun login(email : String, password : String): LoginReponse?{
        val response = loginApi.loginKlant(LoginCredentials(email, password))

        val allowedCustomer = response.body()?.id
        Timber.d(allowedCustomer.toString())
        if (allowedCustomer != null) {
            AuthenticationManager.setCustomer(allowedCustomer)
            return response.body()
        }
        return null;
    }

    suspend fun register(from : RegisterForm): LoginReponse?{
        Timber.d("In Repo for register")
        var obj = RegisterKlant(from.inputFirstName,
            from.inputLastName, from.inputPassword, from.inputEmail, from.inputPhoneNumber )
        Timber.d(obj.toString())

        val response = loginApi.registerKlant(obj)

        val registerdUser = response.body()?.id

        if(registerdUser != null){
            AuthenticationManager.setCustomer(registerdUser)
            return response.body()
        }
        return null
    }
}