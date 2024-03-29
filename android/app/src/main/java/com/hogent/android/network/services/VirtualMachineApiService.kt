package com.hogent.android.network.services

import com.hogent.android.network.Config
import com.hogent.android.network.dtos.request.VirtualMachineCreateRequest
import com.hogent.android.network.dtos.response.DetailsVirtualMachine
import com.hogent.android.network.dtos.response.VirtualMachineReponse
import com.hogent.android.network.dtos.response.VirtualMachineReponseCreate
import com.hogent.android.network.dtos.response.VirtualmachineIndex
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


private const val API = "virtualmachine/"
private val retrofit = Config.createRetrofit(API)

interface VirtualMachineApiService{

    @POST(".")
    suspend fun createVM(@Body vm: VirtualMachineCreateRequest): Response<VirtualMachineReponseCreate>

    @GET("project/{id}")
    suspend fun getByProjectId(@Path("id") id: Int): Response<VirtualMachineReponse?>

    @GET("customer/{id}")
    suspend fun getById(@Path("id") id: Int): Response<DetailsVirtualMachine?>
}

object VirtualMachineApi {
    val retrofitService : VirtualMachineApiService by lazy { retrofit.create(VirtualMachineApiService::class.java) }
}