package com.hogent.android.network.services

import com.hogent.android.network.Config
import com.hogent.android.network.dtos.request.LoginCredentials
import com.hogent.android.network.dtos.request.RegisterKlant
import com.hogent.android.network.dtos.response.LoginReponse
import retrofit2.Response
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

private const val URL = "login/"
private val retrofit = Config.createRetrofit(URL)

interface LoginApiService{

    @POST("login")
    suspend fun loginKlant(@Body dto : LoginCredentials): Response<LoginReponse>

    @POST("register")
    suspend fun registerKlant(@Body dto : RegisterKlant): Response<LoginReponse>
}

object LoginApi{
    val loginApi : LoginApiService by lazy { retrofit.create(LoginApiService::class.java)}
}