package com.lifehive.app

sealed class LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    object LoginClicked : LoginEvent()
    object SignupClicked : LoginEvent()
    object IsLoginFlow: LoginEvent()
    object SignInWithGoogleClicked : LoginEvent()
}