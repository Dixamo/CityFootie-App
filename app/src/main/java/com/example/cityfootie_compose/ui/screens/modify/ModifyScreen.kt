package com.example.cityfootie_compose.ui.screens.modify

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ModifyScreen(goBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar() {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    goBack()
                })

            Spacer(modifier = Modifier.width(18.dp))

            Text(
                text = "¡¡Bienvenido a CityFootie!!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
        }
    }) {
        BodyContent()
    }
}

@Composable
fun BodyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Button(
                onClick = {

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