package com.example.cityfootie_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cityfootie_compose.ui.screens.MainScreen
import com.example.cityfootie_compose.ui.screens.SplashScreen
import com.example.cityfootie_compose.ui.screens.login.LoginScreen
import com.example.cityfootie_compose.ui.screens.map.MapScreen
import com.example.cityfootie_compose.ui.screens.register.RegisterScreen
import com.example.cityfootie_compose.ui.screens.user.UserScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(goMainScreen = {
                navController.popBackStack()
                navController.navigate(AppScreens.MainScreen.route)
            })
        }
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(goLoginScreen = {
                navController.popBackStack()
                navController.navigate(route = AppScreens.LoginScreen.route)
            })
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(
                goUserScreen = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.UserScreen.route)
                },
                goRegisterScreen = {
                    navController.navigate(AppScreens.RegisterScreen.route)
                }
            )
        }

        composable(route = AppScreens.UserScreen.route) {
            UserScreen {
                navController.navigate(AppScreens.MapScreen.route)
            }
        }

        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(
                goBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = AppScreens.MapScreen.route) {
            MapScreen(
                goBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
