package com.hogent.android.network.services

import com.hogent.android.data.entities.Project
import com.hogent.android.network.Config
import com.hogent.android.network.dtos.ProjectDto
import com.hogent.android.network.dtos.response.ProjectResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val API = "project/"
private val retrofit = Config.createRetrofit(API)

interface ProjectApiService {

    @GET
    suspend fun getAll(): List<Project>?

    @GET("customer/{id}")
    suspend fun getByCustomerId(@Path("id") id: Int): Response<ProjectResponse>

    @POST(".")
    suspend fun createProject(@Body proj : ProjectDto): Project?

    }

    object ProjectApi {
        val retrofitService : ProjectApiService by lazy { retrofit.create(ProjectApiService::class.java) }
    }
