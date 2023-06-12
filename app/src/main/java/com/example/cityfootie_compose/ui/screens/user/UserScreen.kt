package com.example.cityfootie_compose.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    goMapScreen: (String) -> Unit,
    goModifyScreen: (String) -> Unit,
    email: String
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
                goMapScreen = { goMapScreen(email) },
                items = bottomNavigationItems,
                selectedItem = selectedItem
            )
        }
    ) {
        BodyContent(goMapScreen, goModifyScreen, email)
    }
}

@Composable
fun BodyContent(
    goMapScreen: (String) -> Unit,
    goModifyScreen: (String) -> Unit,
    email: String,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    var player = userViewModel.player
    var isCompleted = userViewModel.isCompleted
    var isLoading: Boolean = userViewModel.isLoading

    LaunchedEffect(Unit) {
        userViewModel.getPlayer(email)
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

            Spacer(modifier = Modifier.padding(7.dp))
            //ICONO
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        goModifyScreen(email)
                    },
                tint = MaterialTheme.colors.primary
            )
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
                fontSize = 35.sp,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.ExtraBold,
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

                Spacer(modifier = Modifier.padding(35.dp))

                //NOMBRE
                OutlinedTextField(
                    value = "${player.name}",
                    onValueChange = {},
                    enabled = false,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = Color.Gray,
                        disabledLabelColor = MaterialTheme.colors.onBackground,
                        disabledTextColor = MaterialTheme.colors.onBackground
                    ),
                    label = {
                        Text(text = "Nombre", fontSize = 14.sp)
                    }
                )


                Spacer(modifier = Modifier.padding(30.dp))

                //APELLIDOS
                OutlinedTextField(
                    value = "${player.surnames}",
                    onValueChange = {},
                    enabled = false,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = Color.Gray,
                        disabledLabelColor = MaterialTheme.colors.onBackground,
                        disabledTextColor = MaterialTheme.colors.onBackground
                    ),
                    label = {
                        Text(text = "Apellidos", fontSize = 14.sp)
                    }
                )


                Spacer(modifier = Modifier.padding(30.dp))

                //DORSAL
                OutlinedTextField(
                    value = "${player.number}",
                    onValueChange = {},
                    enabled = false,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = Color.Gray,
                        disabledLabelColor = MaterialTheme.colors.onBackground,
                        disabledTextColor = MaterialTheme.colors.onBackground
                    ),
                    label = {
                        Text(text = "Dorsal", fontSize = 14.sp)
                    }
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