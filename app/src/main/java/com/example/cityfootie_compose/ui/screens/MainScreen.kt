package com.example.cityfootie_compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cityfootie_compose.navigation.AppScreens

@Composable
fun MainScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar() {
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = "¡¡Bienvenido a CityFootie!!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
            )
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
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.LoginScreen.route)
                }
            ) {
                Text(
                    text = "Entra Aquí",
                    fontSize = 30.sp
                )
            }
        }
    }
}