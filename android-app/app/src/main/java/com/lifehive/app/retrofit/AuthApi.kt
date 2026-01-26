package com.lifehive.app.retrofit

import com.lifehive.app.data.LoginRequest
import com.lifehive.app.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/notes/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}