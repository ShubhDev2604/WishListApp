package com.lifehive.app.data

data class UserDto(
    val email: String,
    val role: String,
    val enabled: Boolean,
    val provider: String
)
