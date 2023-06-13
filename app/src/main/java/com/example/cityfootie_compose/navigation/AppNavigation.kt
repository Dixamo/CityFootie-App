package com.example.cityfootie_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cityfootie_compose.ui.screens.MainScreen
import com.example.cityfootie_compose.ui.screens.SplashScreen
import com.example.cityfootie_compose.ui.screens.create_football_match.CreateFootballMatchScreen
import com.example.cityfootie_compose.ui.screens.join_to_football_match.JoinToFootballMatchScreen
import com.example.cityfootie_compose.ui.screens.login.LoginScreen
import com.example.cityfootie_compose.ui.screens.map.MapScreen
import com.example.cityfootie_compose.ui.screens.modify.ModifyScreen
import com.example.cityfootie_compose.ui.screens.register.RegisterScreen
import com.example.cityfootie_compose.ui.screens.user.UserScreen


@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(
            route = AppScreens.SplashScreen.route
        ) {
            SplashScreen(
                goMainScreen = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.MainScreen.route)
                }
            )
        }

        composable(
            route = AppScreens.MainScreen.route
        ) {
            MainScreen(
                goLoginScreen = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.LoginScreen.route)
                }
            )
        }

        composable(
            route = AppScreens.LoginScreen.route
        ) {
            LoginScreen(
                goUserScreen = { email ->
                    navController.popBackStack()
                    navController.navigate(AppScreens.UserScreen.route + "/${email}")
                },
                goRegisterScreen = {
                    navController.navigate(AppScreens.RegisterScreen.route)
                }
            )
        }

        composable(
            route = AppScreens.UserScreen.route + "/{email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            UserScreen(
                email = backStackEntry.arguments?.getString("email") ?: "null",
                goMapScreen = { email ->
                    navController.navigate(AppScreens.MapScreen.route + "/${email}")
                },
                goModifyScreen = { email ->
                    navController.navigate(AppScreens.ModifyScreen.route + "/${email}")
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

        composable(
            route = AppScreens.MapScreen.route + "/{email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            MapScreen(
                email = backStackEntry.arguments?.getString("email") ?: "null",
                goBack = {
                    navController.navigateUp()
                },
                goJoinToFootballMatchScreen = { email, latitude, longitude ->
                    navController.navigate(AppScreens.JoinToFootballMatchScreen.route + "/${email}/${latitude}/${longitude}")
                },
                goCreateFootballMatchScreen = { latitude, longitude ->
                    navController.navigate(AppScreens.CreateFootballMatchScreen.route + "/${latitude}/${longitude}")
                }
            )
        }

        composable(
            route = AppScreens.CreateFootballMatchScreen.route + "/{latitude}/{longitude}",
            arguments = listOf(
                navArgument("latitude") {
                    type = NavType.StringType
                },
                navArgument("longitude") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            CreateFootballMatchScreen(
                latitude = backStackEntry.arguments?.getString("latitude") ?: "null",
                longitude = backStackEntry.arguments?.getString("longitude") ?: "null",
                goBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = AppScreens.JoinToFootballMatchScreen.route + "/{email}/{latitude}/{longitude}",
            arguments = listOf(
                navArgument("latitude") {
                    type = NavType.StringType
                },
                navArgument("longitude") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            JoinToFootballMatchScreen(
                email = backStackEntry.arguments?.getString("email") ?: "null",
                latitude = backStackEntry.arguments?.getString("latitude") ?: "null",
                longitude = backStackEntry.arguments?.getString("longitude") ?: "null",
                goBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = AppScreens.ModifyScreen.route + "/{email}", arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            ModifyScreen(
                email = backStackEntry.arguments?.getString("email") ?: "null",
                goBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
