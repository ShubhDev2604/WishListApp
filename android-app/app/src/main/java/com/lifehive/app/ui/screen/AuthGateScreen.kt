package com.lifehive.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lifehive.app.AuthGateState
import com.lifehive.app.viewmodel.AuthGateViewModel

@Composable
fun AuthGateScreen(
    viewModel: AuthGateViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        AuthGateState.Checking -> {

        }

        AuthGateState.AuthenticationSuccess -> {
            LaunchedEffect(Unit) {
                navController.navigate("home_screen") {
                    popUpTo("auth_gate") { inclusive = true }
                }
            }
        }

        AuthGateState.AuthenticationFailure -> {
            LaunchedEffect(Unit) {
                navController.navigate("login_screen") {
                    popUpTo("auth_gate") { inclusive = true }
                }
            }
        }

        AuthGateState.Loading -> {
            LoadingScreenUI()
        }
    }
}