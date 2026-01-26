package com.lifehive.app.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifehive.app.LoginEvent
import com.lifehive.app.data.LoginResponse
import com.lifehive.app.data.LoginUiState
import com.lifehive.app.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(LoginUiState())
    val uiState: State<LoginUiState> = _uiState

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(
                    email = event.value,
                    errorMessage = null
                )
            }

            is LoginEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(
                    password = event.value,
                    errorMessage = null
                )
            }

            LoginEvent.LoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.value = state.copy(
                errorMessage = "Email and password cannot be empty"
            )
            return
        }

        _uiState.value = state.copy(isLoading = true)

        viewModelScope.launch {
//            val result = authRepository.login(
//                state.email,
//                state.password
//            )
            if (state.email == "Admin" && state.password == "Admin@123") {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoggedIn = true     // 🔥 THIS TRIGGERS NAVIGATION
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Invalid credentials"
                )
            }
//            result.onSuccess {
//                _uiState.value = _uiState.value.copy(
//                    isLoading = false
//                )
//                // success → navigation will happen from UI
//            }.onFailure { error ->
//                _uiState.value = _uiState.value.copy(
//                    isLoading = false,
//                    errorMessage = error.message ?: "Something went wrong"
//                )
//            }
        }
    }
}
