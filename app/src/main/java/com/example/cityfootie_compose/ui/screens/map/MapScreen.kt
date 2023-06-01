package com.example.cityfootie_compose.ui.screens.map

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen(
    goBack: () -> Unit,
    goFootballMatch: () -> Unit
) {
    val selectedItem = remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            val bottomNavigationItems = listOf(
                BottomNavigationItem(
                    title = "Profile",
                    icon = Icons.Outlined.Person,
                    color = Color.White
                ),
                BottomNavigationItem(
                    title = "Map",
                    icon = Icons.Filled.Map,
                    color = Color.White
                )
            )
            BottomNavigationBar(
                goBack = goBack,
                items = bottomNavigationItems,
                selectedItem = selectedItem
            )
        }
    ) {
        BodyContent(goBack, goFootballMatch)
    }
}

@Composable
fun BodyContent(
    goBack: () -> Unit,
    goFootballMatch: () -> Unit,
    mapViewModel: MapViewModel = hiltViewModel()
) {
    val marker1 = LatLng(40.35105282074944, -3.700295054544909)
    val marker2 = LatLng(40.354911925370416, -3.7005084128650765)
    val marker3 = LatLng(40.34924765147908, -3.693923893405747)
    val marker4 = LatLng(40.348042934346815, -3.691662785268137)
    val marker5 = LatLng(40.39041751084235, -3.6600595654305264)
    val marker6 = LatLng( 40.45055718401559, -3.7117661601773517)
    val marker7 = LatLng( 40.460822724171685, -3.653721323776217)
    val marker8 = LatLng( 40.50139811541079, -3.6978673697183617)
    val marker9 = LatLng( 40.355669347020175, -3.680274938148021)
    val marker10 = LatLng( 40.3244730386376, -3.733080010735)
    val marker11 = LatLng( 40.31078603079951, -3.877981530609243)
    val marker12 = LatLng( 40.3220462657423, -3.7616192611944146)
    val marker13 = LatLng( 40.33175179320058, -3.7449872851827015)
    val marker14 = LatLng( 40.344607689124736, -3.7542273213564163)
    val marker15 = LatLng( 40.29910372790963, -3.7568766516572127)
    val marker16 = LatLng( 40.33633376914984, -3.7764898101040103)
    val marker17 = LatLng( 40.322766310753025, -3.778236423660193)
    val marker18 = LatLng( 40.48751113221966, -3.726553869961957)
    val marker19 = LatLng( 40.368551136085586, -3.7113168361285678)
    val marker20 = LatLng( 40.36705638474042, -3.7076461563291905)


    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(marker1,12f)
    }
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.HYBRID)) }
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }

    val markerClicked = remember { mutableStateOf(false) }

    Switch(checked = uiSettings.zoomControlsEnabled,
        onCheckedChange = {
            uiSettings = uiSettings.copy(zoomControlsEnabled = it)
        }
    )

    GoogleMap(
        modifier = Modifier.padding(bottom = 50.dp),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        FootieMarker(
            position = marker1,
            markerClicked = markerClicked,
            direction = "Dirección: C. de la Vírgen de los Desamparados, 20"
        )
        
        FootieMarker(
            position = marker2,
            markerClicked = markerClicked,
            direction = "Dirección: C. Hermandad de Donantes de Sangre, 7"
        )
        
        FootieMarker(
            position = marker3,
            markerClicked = markerClicked,
            direction = "Dirección: C. Santiago Amón, 8"
        )
        
        FootieMarker(
            position = marker4,
            markerClicked = markerClicked,
            direction = "Dirección: C. Sta. Florencia, 27"
        )
        
        FootieMarker(
            position = marker5,
            markerClicked = markerClicked,
            direction = "Dirección: C. del Payaso Fofó, 9, Madrid"
        )
        
        FootieMarker(
            position = marker6,
            markerClicked = markerClicked,
            direction = "Dirección: P.º de Juan XXIII, 39"
        )
        
        FootieMarker(
            position = marker7,
            markerClicked = markerClicked,
            direction = "Dirección: C. Puerto Real, 7"
        )
        
        FootieMarker(
            position = marker8,
            markerClicked = markerClicked,
            direction = "Dirección: Puente de Tres Olivos"
        )
        
        FootieMarker(
            position = marker9,
            markerClicked = markerClicked,
            direction = "Dirección: Av. de los Rosales, 91"
        )
        
        FootieMarker(
            position = marker10,
            markerClicked = markerClicked,
            direction = "Dirección: C. Juan Bautista de Alderete, 8"
        )
        
        FootieMarker(
            position = marker11,
            markerClicked = markerClicked,
            direction = "Dirección: C. Río Guadiana, 10"
        )
        
        FootieMarker(
            position = marker12,
            markerClicked = markerClicked,
            direction = "Dirección: C. Constitución de Cádiz, 5"
        )
        
        FootieMarker(
            position = marker13,
            markerClicked = markerClicked,
            direction = "Dirección: Avenida Alemania, 2"
        )
        
        FootieMarker(
            position = marker14,
            markerClicked = markerClicked,
            direction = "Dirección: Av. de María Guerrero, 10"
        )
        
        FootieMarker(
            position = marker15,
            markerClicked = markerClicked,
            direction = "Dirección: Paseo de Ramón González Peña, 3"
        )
        
        FootieMarker(
            position = marker16,
            markerClicked = markerClicked,
            direction = "Dirección: C. Zurbarán, 3"
        )
        
        FootieMarker(
            position = marker17,
            markerClicked = markerClicked,
            direction = "Dirección: C. de Francisco Largo Caballero, 21"
        )
        
        FootieMarker(
            position = marker18,
            markerClicked = markerClicked,
            direction = "Dirección: C. del Cerro Minguete, 156"
        )

        FootieMarker(
            position = marker19,
            markerClicked = markerClicked,
            direction = "Dirección: C. del Camino Viejo de Villaverde, 32"
        )

        FootieMarker(
            position = marker20,
            markerClicked = markerClicked,
            direction = "Dirección: C. Rda. de las Cooperativas, 10"
        )

        if (markerClicked.value) {
            LaunchedEffect(Unit) {
                goFootballMatch()
            }
        }
    }
}

@Composable
fun FootieMarker(
    position: LatLng,
    markerClicked: MutableState<Boolean>,
    direction: String
) {
    MarkerInfoWindow(
        state = MarkerState(position = position),
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
        onInfoWindowClick = {
            markerClicked.value = true
        }
    ) { marker ->
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = direction,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(200.dp),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}
@Composable
fun BottomNavigationBar(
    goBack: () -> Unit,
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
                        goBack()
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