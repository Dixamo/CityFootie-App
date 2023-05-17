package com.example.cityfootie_compose.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cityfootie_compose.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserScreen(
    navController: NavController,
    username: String,
    dorsal: String
) {
    val selectedItem = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            val bottomNavigationItems = listOf(
                BottomNavigationItem("Home", R.drawable.logo),
                BottomNavigationItem("Favorites", R.drawable.logo),
                BottomNavigationItem("Profile", R.drawable.logo)
            )

            BottomNavigationBar(items = bottomNavigationItems, selectedItem = selectedItem)
        }
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
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: MutableState<Int>
) {
    BottomNavigation(
        modifier = Modifier.background(color = Color.White)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedItem.value == index,
                onClick = { selectedItem.value = index },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) }
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val icon: Int
)