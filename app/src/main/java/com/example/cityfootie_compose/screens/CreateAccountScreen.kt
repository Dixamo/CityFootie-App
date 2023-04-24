package com.example.cityfootie_compose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateAccountScreen(navController: NavController){
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
        BodyContentAccount(navController)
    }
}

@Composable
fun BodyContentAccount(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            "Crear cuenta",
            fontSize = 40.sp,
        )

        Spacer(modifier = Modifier.padding(15.dp))

        //NOMBRE
        var name1 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name1,
            onValueChange = { name1 = it },
            label = { Text("Nombre") },
            modifier = Modifier.width(350.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))

        //APELLIDOS
        var name2 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name2,
            onValueChange = { name2 = it },
            label = { Text("Apellidos") },
            modifier = Modifier.width(350.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))

        //FECHA DE NACIMIENTO
        var name3 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Icon"
                )
            },
            value = name3,
            onValueChange = { name3 = it },
            label = { Text("Fecha de Nacimiento") },
            modifier = Modifier.width(350.dp),
        )
        Spacer(modifier = Modifier.padding(4.dp))

        //CORREO ELECTRÓNICO
        var name4 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            value = name4,
            onValueChange = { name4 = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        )
        Spacer(modifier = Modifier.padding(25.dp))


        //NOMBRE DE USUARIO
        var name5 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon"
                )
            },
            value = name5,
            onValueChange = { name5 = it },
            label = { Text("* Nombre de usuario") },
            modifier = Modifier.width(350.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))

        //CONTRASEÑA
        var pass by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Lock Icon"
                )
            },
            value = pass,
            onValueChange = { pass = it },
            label = { Text("* Contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        )
        Spacer(modifier = Modifier.padding(25.dp))


        //DORSAL
        var name6 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name6,
            onValueChange = { name6 = it },
            label = { Text("Dorsal") },
            modifier = Modifier.width(350.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.padding(4.dp))

        //JUGADOR FAVORITO
        var name7 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name7,
            onValueChange = { name7 = it },
            label = { Text("Jugador favorito") },
            modifier = Modifier.width(350.dp)
        )
        Spacer(modifier = Modifier.padding(4.dp))

        //EQUIPO FAVORITO
        var name8 by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = name8,
            onValueChange = { name8 = it },
            label = { Text("Equipo favorito") },
            modifier = Modifier.width(350.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "* = Obligatorio")
        


        //BOTÓN
        Button(onClick = { }, enabled = name5.length > 4 && pass.length > 8 ) {
            Text(text = "Crear cuenta")
        }
    }
}
