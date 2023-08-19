package com.hogent.android.network.services


import com.hogent.android.data.entities.Customer
import com.hogent.android.network.Config
import com.hogent.android.network.dtos.request.CustomerUpdate
import com.hogent.android.network.dtos.response.CustomerEditedReponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

private const val url = "user/android/"
private val retrofit = Config.createRetrofit(url)

interface CustomerService{

    @GET("{id}")
    suspend fun getCustomer(@Path("id") id : Int): Response<Customer?>

    @PUT("{id}")
    suspend fun  updateCustomer(@Path("id") id: Int, @Body Customer: CustomerUpdate): Response<CustomerEditedReponse?>
}

object CustomerApi {
    val customerService : CustomerService by lazy {retrofit.create(CustomerService::class.java)}
}