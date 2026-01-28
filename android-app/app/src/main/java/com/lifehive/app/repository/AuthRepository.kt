package com.lifehive.app.repository

import com.lifehive.app.data.LoginRequest
import com.lifehive.app.data.LoginResponse
import com.lifehive.app.data.SessionResult
import com.lifehive.app.retrofit.AuthApi


class AuthRepository(
    private val authApi: AuthApi
) {

    suspend fun signup(
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = authApi.signup(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                Result.failure(
                    Exception(
                        response.errorBody()?.string() ?: "Login failed"
                    )
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = authApi.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                Result.failure(
                    Exception(
                        response.errorBody()?.string() ?: "Login failed"
                    )
                )
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun authChecker(): SessionResult {
        return try {
            val response = authApi.authChecker()
            if(response.isSuccessful) {
                SessionResult.Valid
            } else {
                SessionResult.Invalid
            }
        } catch (e: Exception) {
            SessionResult.Invalid
        }
    }
}
