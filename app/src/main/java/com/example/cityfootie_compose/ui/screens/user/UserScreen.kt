package com.example.cityfootie_compose.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.screens.map.MapViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserScreen(goMapScreen: () -> Unit) {

    val selectedItem = remember { mutableStateOf(0) }

    Scaffold(

        bottomBar = {
            val bottomNavigationItems = listOf(
                BottomNavigationItem(
                    title = "Profile",
                    icon = Icons.Filled.Person,
                    color = Color.White
                ),
                BottomNavigationItem(
                    title = "Map",
                    icon = Icons.Outlined.Map,
                    color = Color.White
                )
            )
            BottomNavigationBar(
                goMapScreen = goMapScreen,
                items = bottomNavigationItems,
                selectedItem = selectedItem
            )
        }
    ) {
        BodyContent(goMapScreen)
    }
}

@Composable
fun BodyContent(
    goMapScreen: () -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "BIENVENIDO username",
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "dorsal",
            color = MaterialTheme.colors.primary,
            fontSize = 50.sp,
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun BottomNavigationBar(
    goMapScreen: () -> Unit,
    items: List<BottomNavigationItem>,
    selectedItem: MutableState<Int>
) {
    BottomNavigation(
        modifier = Modifier.background(color = Color.White)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedItem.value == index,
                onClick = {
                    if (selectedItem.value != index) {
                        goMapScreen()
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = item.color
                    )
                },
                label = { Text(text = item.title) }
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val navigation: Unit? = null
)