package com.lifehive.app.data

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    var emailError: String? = null,
    var passwordError: String? = null,
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    var generalError: String? = null,
    var isLoginFlow: Boolean = true
)
