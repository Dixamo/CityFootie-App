package com.example.cityfootie_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cityfootie_compose.ui.screens.MainScreen
import com.example.cityfootie_compose.ui.screens.SplashScreen
import com.example.cityfootie_compose.ui.screens.login.LoginScreen
import com.example.cityfootie_compose.ui.screens.register.RegisterScreen
import com.example.cityfootie_compose.ui.screens.user.UserScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = AppScreens.UserScreen.route + "/{username}/{dorsal}",
            arguments = listOf(
                navArgument(name = "username") { type = NavType.StringType },
                navArgument(name = "dorsal") { type = NavType.StringType }
            )) {
            UserScreen(navController, username = it.arguments!!.getString("username", "Nombre"), dorsal = it.arguments!!.getString("dorsal", "Dorsal"))
        }
    }
}
