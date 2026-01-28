package com.lifehive.app.data

data class LoginResponse(
    val accessToken: String,
    val tokenType: String,
    val email: String,
    val role: String,
    val enabled: Boolean,
    val expiresIn: Long
)