package com.lifehive.app.data

data class LoginResponse(
    val accessToken: String,
    val user: UserDto
)