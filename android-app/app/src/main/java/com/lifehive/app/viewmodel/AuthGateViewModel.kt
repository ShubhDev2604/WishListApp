package com.lifehive.app.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifehive.app.AuthGateState
import com.lifehive.app.TokenManager
import com.lifehive.app.data.SessionResult
import com.lifehive.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthGateViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _state = MutableStateFlow<AuthGateState>(
        AuthGateState.Checking
    )
    val state: StateFlow<AuthGateState> = _state
    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            when (authRepository.authChecker()) {
                SessionResult.Valid -> {
                    _state.value = AuthGateState.AuthenticationSuccess
                }
                SessionResult.Invalid -> {
                    tokenManager.clearToken()
                    _state.value = AuthGateState.AuthenticationFailure
                }
            }
        }
    }
}