package com.example.cityfootie_compose.navigation

sealed class AppScreens(val route: String) {
    //WAAAAASIIIIII
    object SplashScreen : AppScreens("splash_screen")
    object MainScreen : AppScreens("main_screen")
    object LoginScreen : AppScreens("login_screen")
    object RegisterScreen : AppScreens("register_screen")
    object UserScreen : AppScreens("user_screen")
    object ModifyScreen : AppScreens("modify_screen")
    object MapScreen : AppScreens("map_screen")
    object CreateFootballMatchScreen : AppScreens("create_football_match_screen")
    object JoinToFootballMatchScreen : AppScreens("join_to_football_match_screen")
}