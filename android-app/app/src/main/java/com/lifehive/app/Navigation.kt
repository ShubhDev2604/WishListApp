package com.lifehive.app

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifehive.app.ui.screen.AddEditDetailView
import com.lifehive.app.ui.screen.HomeView
import com.lifehive.app.ui.screen.LoginView
import com.lifehive.app.ui.screen.Screen
import com.lifehive.app.ui.screen.WishDetailView
import com.lifehive.app.viewmodel.WishViewModel

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(),
               navController: NavHostController = rememberNavController(),
               isDarkTheme: Boolean,
               onToggleTheme: () -> Unit){
    NavHost(
        navController= navController,
        startDestination = Screen.LoginScreen.route
    ){
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginView(
                onLoginSuccess = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.LoginScreen.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screen.HomeScreen.route + "?snackbarMessage={snackbarMessage}",
            arguments = listOf(
                navArgument("snackbarMessage") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ){
            val snackbarMessage = it.arguments?.getString("snackbarMessage")
            HomeView(viewModel, navController, snackbarMessage, isDarkTheme, onToggleTheme)
        }

        composable(
            Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ){entry->
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = viewModel , navController = navController)
        }

        composable(
            Screen.ViewScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ){entry->
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            WishDetailView(id = id, viewModel = viewModel , navController = navController)
        }
    }
}