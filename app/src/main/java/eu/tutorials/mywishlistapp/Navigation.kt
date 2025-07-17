package eu.tutorials.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(),
               navController: NavHostController = rememberNavController(),
               isDarkTheme: Boolean,
               onToggleTheme: () -> Unit){
    NavHost(
        navController= navController,
        startDestination = Screen.HomeScreen.route
    ){
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

        composable(Screen.AddScreen.route + "/{id}",
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
    }
}