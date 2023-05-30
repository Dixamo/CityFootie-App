package com.example.cityfootie_compose.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Map
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserScreen(
    goMapScreen: () -> Unit,
    goModifyScreen: () -> Unit,
    email: String,
    password: String
) {

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
        BodyContent(goMapScreen, goModifyScreen, email, password)
    }
}

@Composable
fun BodyContent(
    goMapScreen: () -> Unit,
    goModifyScreen: () -> Unit,
    email: String,
    password: String,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    var player = userViewModel.player
    var isCompleted = userViewModel.isCompleted
    var isLoading: Boolean = userViewModel.isLoading

    LaunchedEffect(Unit) {
        userViewModel.getPlayer(email, password)
        while (player == null) {
            delay(100)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {

        if (player != null) {

            //ICONO
            Button(
                onClick = {
                    goModifyScreen()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    }

    //ENCABEZADO
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        if (player != null) {

            //NOMBRE DE USUARIO
            Text(
                text = "${player.username}",
                color = MaterialTheme.colors.primary,
                fontSize = 30.sp,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Left
            )

            //EMAIL
            Text(
                text = "${player.email}",
                color = MaterialTheme.colors.onBackground,
                fontSize = 23.sp,
                //style = MaterialTheme.typography.overline,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.primary,
                thickness = 2.dp
            )
        }


        //CONTENIDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (player != null) {

                Spacer(modifier = Modifier.padding(60.dp))

                //NOMBRE
                Text(
                    text = "Nombre: ${player.name}",
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 20.sp,
                    //style = MaterialTheme.typography.overline,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                )


                Spacer(modifier = Modifier.padding(30.dp))

                //APELLIDOS
                Text(
                    text = "Apellidos: ${player.surnames}",
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 20.sp,
                    //style = MaterialTheme.typography.overline,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(30.dp))

                //DORSAL
                Text(
                    text = "Dorsal: ${player.number}",
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 20.sp,
                    //style = MaterialTheme.typography.overline,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                )
            }
        }
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