package com.lifehive.app.data

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val generalError: String? = null
)
