package com.lifehive.app.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifehive.app.LoginEvent
import com.lifehive.app.TokenManager
import com.lifehive.app.data.LoginUiState
import com.lifehive.app.repository.AuthRepository
import com.lifehive.app.validators.EmailValidator
import com.lifehive.app.validators.PasswordValidator
import com.lifehive.app.validators.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = mutableStateOf(LoginUiState())
    val uiState: State<LoginUiState> = _uiState

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(
                    email = event.value,
                    emailError = null
                )
            }

            is LoginEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(
                    password = event.value,
                    passwordError = null
                )
            }

            LoginEvent.LoginClicked -> {
                login()
            }

            LoginEvent.SignupClicked -> {
                signup()
            }

            LoginEvent.SignInWithGoogleClicked -> {

            }

            LoginEvent.IsLoginFlow -> {
                _uiState.value = _uiState.value.copy(
                    isLoginFlow = !_uiState.value.isLoginFlow
                )
            }
        }
    }

    private fun login() {
        val state = _uiState.value

        val emailResult = EmailValidator.validate(state.email)
        val passwordResult = PasswordValidator.validate(state.password)

        var hasError = false

        if (emailResult is ValidationResult.Invalid) {
            hasError = true
            _uiState.value = _uiState.value.copy(
                email = state.email,
                emailError = emailResult.message
            )
        }

        if (passwordResult is ValidationResult.Invalid) {
            hasError = true
            _uiState.value = _uiState.value.copy(
                email = state.email,
                emailError = passwordResult.message
            )
        }

        if (hasError) return

        _uiState.value = state.copy(isLoading = true)

        viewModelScope.launch {
            val result = authRepository.login(
                state.email,
                state.password
            )
//            if (state.email == "Admin" && state.password == "Admin@123") {
//                _uiState.value = _uiState.value.copy(
//                    isLoading = false,
//                    isLoggedIn = true     // 🔥 THIS TRIGGERS NAVIGATION
//                )
//            } else {
//                _uiState.value = _uiState.value.copy(
//                    isLoading = false,
//                    errorMessage = "Invalid credentials"
//                )
//            }
            result.onSuccess { response ->
                tokenManager.saveToken(
                    response.accessToken,
                    response.tokenType
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoginSuccess = true
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    generalError = error.message ?: "Something went wrong"
                )
            }
        }
    }

    private fun signup() {
        val state = _uiState.value


        val emailResult = EmailValidator.validate(state.email)
        val passwordResult = PasswordValidator.validate(state.password)

        var hasError = false

        if (emailResult is ValidationResult.Invalid) {
            hasError = true
            _uiState.value = _uiState.value.copy(
                email = state.email,
                emailError = emailResult.message
            )
        }

        if (passwordResult is ValidationResult.Invalid) {
            hasError = true
            _uiState.value = _uiState.value.copy(
                email = state.email,
                emailError = passwordResult.message
            )
        }

        if (hasError) return

        _uiState.value = state.copy(isLoading = true)

        viewModelScope.launch {
            val result = authRepository.signup(
                state.email,
                state.password
            )
            result.onSuccess { response ->
                tokenManager.saveToken(
                    response.accessToken,
                    response.tokenType
                )
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoginSuccess = true
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    generalError = error.message ?: "Something went wrong"
                )
            }
        }
    }
}
