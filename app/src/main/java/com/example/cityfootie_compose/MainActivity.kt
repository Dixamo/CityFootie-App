package com.example.cityfootie_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cityfootie_compose.navigation.AppNavigation
import com.example.cityfootie_compose.ui.screens.LoginViewModel
import com.example.cityfootie_compose.ui.screens.login.LoginViewModel
import com.example.cityfootie_compose.ui.theme.CityFootieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        //val loginViewModel: LoginViewModel by viewModels()
=======
>>>>>>> Stashed changes
        setContent {
            CityFootieComposeTheme() {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation()
                    AppNavigation(navController)
                }
            }
        }
    }
}