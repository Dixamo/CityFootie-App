package com.example.cityfootie_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cityfootie_compose.ui.screens.*

import com.example.cityfootie_compose.ui.screens.login.LoginScreen
import com.example.cityfootie_compose.ui.screens.register.CreateAccountScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.CreateAccountScreen.route) {
            CreateAccountScreen(navController)
        }
    }
}
