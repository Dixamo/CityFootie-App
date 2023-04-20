package com.example.cityfootie_compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cityfootie_compose.navigation.AppScreens

@Composable
fun LoginScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                })
        }
    }) {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            "Inicio de Sesión",
            fontSize = 40.sp,
        )
        Spacer(modifier = Modifier.padding(25.dp))

        var name by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Usuario") },
        )

        Spacer(modifier = Modifier.padding(8.dp))

        var pass by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(onClick = { }, enabled = name.length > 1 && pass.length > 8) {
            Text(text = "Inicia sesión")
        }

        ClickableText(
            text = AnnotatedString("¿No tienes cuenta? Créate una."),
            onClick = {
                navController.navigate(route = AppScreens.CreateAccountScreen.route)
            }
        )
    }
}