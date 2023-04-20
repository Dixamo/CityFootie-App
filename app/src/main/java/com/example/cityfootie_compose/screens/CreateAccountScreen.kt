package com.example.cityfootie_compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateAccountScreen(navController: NavController){
    Scaffold {
        TopAppBar(backgroundColor = Color.Green) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                })
        }
        BodyContentAccount(navController)
    }
}

@Composable
fun BodyContentAccount(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            "Crear cuenta",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.padding(15.dp))

        var name1 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name1,
            onValueChange = { name1 = it },
            label = { Text("Nombre") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        var name2 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name2,
            onValueChange = { name2 = it },
            label = { Text("Apellidos") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        var name3 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name3,
            onValueChange = { name3 = it },
            label = { Text("Fecha de nacimiento") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        var name4 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name4,
            onValueChange = { name4 = it },
            label = { Text("Nombre de usuario") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        var name5 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name5,
            onValueChange = { name5 = it },
            label = { Text("Correo electrónico") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        var pass by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña")},
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(8.dp))

        var name6 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name6,
            onValueChange = { name6 = it },
            label = { Text("Dorsal") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        var name7 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name7,
            onValueChange = { name7 = it },
            label = { Text("Jugador favorito") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        var name8 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name8,
            onValueChange = { name8 = it },
            label = { Text("Equipo favorito") },
        )
        Spacer(modifier = Modifier.padding(4.dp))

        Button(onClick = { }, enabled = name1.length > 1 && pass.length > 8 ) {
            Text(text = "Crear cuenta")
        }
    }
}
