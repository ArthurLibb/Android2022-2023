package com.hogent.android.network.services

import com.hogent.android.data.entities.Project
import com.hogent.android.network.Config
import com.hogent.android.network.dtos.request.CreateProjectDto
import com.hogent.android.network.dtos.request.ProjectDetailsRequest
import com.hogent.android.network.dtos.response.ProjectDetailResponse
import com.hogent.android.network.dtos.response.ProjectResponse
import com.hogent.android.network.dtos.response.ProjectResponseAfterCreate
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

    @GET("customerget/{id}")
    suspend fun getByCustomerId(@Path("id") id: Int): Response<ProjectResponse>

    @POST("create")
    suspend fun createProject(@Body proj : CreateProjectDto): Response<ProjectResponseAfterCreate>

    @GET("android/{id}")
    suspend fun getProjectDetails(@Body proj : ProjectDetailsRequest): Response<ProjectDetailResponse>

    }

    object ProjectApi {
        val retrofitService : ProjectApiService by lazy { retrofit.create(ProjectApiService::class.java) }
    }
