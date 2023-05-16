package com.example.cityfootie_compose.ui.screens.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cityfootie_compose.navigation.AppScreens
import com.example.cityfootie_compose.ui.screens.login.LoginViewModel

@Composable
fun UserScreen(
    navController: NavController,
    username: String,
    dorsal: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "BIENVENIDO $username",
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = dorsal,
            color = MaterialTheme.colors.primary,
            fontSize = 50.sp,
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.ExtraBold
        )
    }
}