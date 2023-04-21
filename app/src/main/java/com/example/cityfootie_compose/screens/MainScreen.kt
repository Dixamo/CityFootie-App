package com.example.cityfootie_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cityfootie_compose.navigation.AppScreens

@Composable
fun MainScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar() {
            Text(text = "Pantalla Principal")
        }
    }){
        MainBodyContent(navController)
    }
}

@Composable
fun MainBodyContent(navController: NavController){
    AsyncImage(
        model = "https://w0.peakpx.com/wallpaper/941/855/HD-wallpaper-football-field-aerial-view-trees-playground-green.jpg",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(10000.dp)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Button(onClick = {
                navController.navigate(route = AppScreens.LoginScreen.route)
            }) {
                Text(text = "Inicia Sesión")
            }
        }
    }
}