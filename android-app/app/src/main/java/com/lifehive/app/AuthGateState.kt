package com.lifehive.app

sealed class AuthGateState {
    object Loading: AuthGateState()
    object Checking: AuthGateState()
    object AuthenticationSuccess: AuthGateState()
    object AuthenticationFailure: AuthGateState()
}