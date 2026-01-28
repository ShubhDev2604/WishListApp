package com.lifehive.app.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lifehive.app.LoginEvent
import com.lifehive.app.R
import com.lifehive.app.viewmodel.LoginViewModel


@Composable
fun LoginView(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.surface
            ),
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .weight(0.45f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.login_screen_image_short)
                .build(),
            contentDescription = "LifeHive cover",
            contentScale = ContentScale.Crop
        )


        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f),
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(
                topStart = 28.dp,
                topEnd = 28.dp
            )
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(LoginEvent.EmailChanged(it))
                    },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        textColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(LoginEvent.PasswordChanged(it))
                    },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        textColor = MaterialTheme.colorScheme.onSurface,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if(state.isLoginFlow)
                            viewModel.onEvent(LoginEvent.LoginClicked)
                        else {
                            viewModel.onEvent(LoginEvent.SignupClicked)
                        }
                    },
                    enabled = !state.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    if(state.isLoginFlow) {
                        Text("Login")
                    } else {
                        Text("Sign Up")
                    }

                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(state.isLoginFlow) {
                        Text(
                            text = "Don’t have an account?",
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "Sign Up",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(LoginEvent.IsLoginFlow)
                            }
                        )
                    } else {
                        Text(
                            text = "Already have an account?",
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "Login",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(LoginEvent.IsLoginFlow)
                            }
                        )
                    }
                }
//                OutlinedButton(
//                    onClick = {},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(48.dp),
//                    colors = ButtonDefaults.outlinedButtonColors(
//                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
//                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                    ),
//                    border = BorderStroke(
//                        1.dp,
//                        MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
//                    )
//                ) {
//                    Text("Sign in with Google")
//                }
            }
        }

        if(!state.emailError.isNullOrEmpty() || !state.passwordError.isNullOrEmpty() || !state.generalError.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            val message: String = if(!state.emailError.isNullOrEmpty())
                state.emailError!!
            else (if(!state.passwordError.isNullOrEmpty())
                state.passwordError
            else
                state.generalError).toString()

            Toast.makeText(
                LocalContext.current,
                message,
                Toast.LENGTH_SHORT
            ).show()
            state.passwordError = null
            state.emailError = null
            state.generalError = null
        }

        LaunchedEffect(state.isLoginSuccess) {
            if (state.isLoginSuccess) {
                onLoginSuccess()
            }
        }
    }
}