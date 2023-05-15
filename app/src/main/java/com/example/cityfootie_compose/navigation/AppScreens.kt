package com.example.cityfootie_compose.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object MainScreen : AppScreens("main_screen")
    object LoginScreen : AppScreens("login_screen")
    object CreateAccountScreen : AppScreens("create_account_screen")
    object Prueba : AppScreens("prueba")
}