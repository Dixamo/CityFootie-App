package com.example.cityfootie_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cityfootie_compose.ui.screens.MainScreen
import com.example.cityfootie_compose.ui.screens.SplashScreen
import com.example.cityfootie_compose.ui.screens.footballMatch.FootballMatchScreen
import com.example.cityfootie_compose.ui.screens.login.LoginScreen
import com.example.cityfootie_compose.ui.screens.map.MapScreen
import com.example.cityfootie_compose.ui.screens.modify.ModifyScreen
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
                goUserScreen = { email, password ->
                    navController.popBackStack()
                    navController.navigate(
                        AppScreens.UserScreen.route + "/${email}/${password}"
                    )
                },
                goRegisterScreen = {
                    navController.navigate(AppScreens.RegisterScreen.route)
                }
            )
        }

        composable(
            route = AppScreens.UserScreen.route + "/{email}/{password}", arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                },
                navArgument("password") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            UserScreen(
                email = backStackEntry.arguments?.getString("email") ?: "null",
                password = backStackEntry.arguments?.getString("password") ?: "null",

                goMapScreen = {
                    navController.navigate(AppScreens.MapScreen.route)
                },

                goModifyScreen = {
                    navController.navigate(AppScreens.ModifyScreen.route)
                }
            )
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
                },
                goFootballMatch = {
                    navController.navigate(AppScreens.FootBallMatchScreen.route)
                }
            )
        }

        composable(route = AppScreens.FootBallMatchScreen.route) {
            FootballMatchScreen(
                goBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = AppScreens.ModifyScreen.route) {
            ModifyScreen(
                goBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
