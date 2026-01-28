package com.lifehive.app.ui.screen

sealed class Screen(val route:String) {
    object AuthGate: Screen("auth_gate")
    object HomeScreen: Screen("home_screen")
    object AddScreen: Screen("add_screen")
    object ViewScreen: Screen("view_screen")
    object LoginScreen: Screen("login_screen")
}