package com.example.cityfootie_compose.ui.screens.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.components.bottom_navigation_bar.BottomNavigationBar
import com.example.cityfootie_compose.ui.components.bottom_navigation_bar.BottomNavigationItem
import com.example.cityfootie_compose.ui.components.footie_marker.FootieMarker
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen(
    email: String,
    goBack: () -> Unit,
    goJoinToFootballMatchScreen: (String, String, String) -> Unit,
    goCreateFootballMatchScreen: (String, String) -> Unit
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
                navMethod = goBack,
                items = bottomNavigationItems,
                selectedItem = selectedItem
            )
        }
    ) {
        BodyContent(email, goJoinToFootballMatchScreen, goCreateFootballMatchScreen)
    }
}

@Composable
fun BodyContent(
    email: String,
    goJoinToFootballMatchScreen: (String, String, String) -> Unit,
    goCreateFootballMatchScreen: (String, String) -> Unit,
    mapViewModel: MapViewModel = hiltViewModel()
) {
    val isLoading: Boolean = mapViewModel.isLoading
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    val marker1 = LatLng(40.35105282074944, -3.700295054544909)
    val marker2 = LatLng(40.354911925370416, -3.7005084128650765)
    val marker3 = LatLng(40.34924765147908, -3.693923893405747)
    val marker4 = LatLng(40.348042934346815, -3.691662785268137)
    val marker5 = LatLng(40.39041751084235, -3.6600595654305264)
    val marker6 = LatLng(40.45055718401559, -3.7117661601773517)
    val marker7 = LatLng(40.460822724171685, -3.653721323776217)
    val marker8 = LatLng(40.50139811541079, -3.6978673697183617)
    val marker9 = LatLng(40.355669347020175, -3.680274938148021)
    val marker10 = LatLng(40.3244730386376, -3.733080010735)
    val marker11 = LatLng(40.31078603079951, -3.877981530609243)
    val marker12 = LatLng(40.3220462657423, -3.7616192611944146)
    val marker13 = LatLng(40.33175179320058, -3.7449872851827015)
    val marker14 = LatLng(40.344607689124736, -3.7542273213564163)
    val marker15 = LatLng(40.29910372790963, -3.7568766516572127)
    val marker16 = LatLng(40.33633376914984, -3.7764898101040103)
    val marker17 = LatLng(40.322766310753025, -3.778236423660193)
    val marker18 = LatLng(40.48751113221966, -3.726553869961957)
    val marker19 = LatLng(40.368551136085586, -3.7113168361285678)
    val marker20 = LatLng(40.36705638474042, -3.7076461563291905)


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker1, 12f)
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
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker2,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker3,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker4,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker5,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker6,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker7,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker8,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker9,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker10,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker11,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker12,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker13,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker14,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker15,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker16,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker17,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker18,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker19,
            markerClicked = markerClicked
        )

        FootieMarker(
            position = marker20,
            markerClicked = markerClicked
        )

        var isCompleted = mapViewModel.isCompleted
        val isSuccessful = mapViewModel.isSuccessful
        val isError = mapViewModel.isError
        val latitude = mapViewModel.markerLatitude
        val longitude = mapViewModel.markerLongitude

        LaunchedEffect(isCompleted) {
            isCompleted = false
            markerClicked.value = false
            if (isSuccessful) {
                markerClicked.value = false
                mapViewModel.resetValues()
                goJoinToFootballMatchScreen(email, latitude.toString(), longitude.toString())
            } else if (isError) {
                markerClicked.value = false
                mapViewModel.resetValues()
                goCreateFootballMatchScreen(latitude.toString(), longitude.toString())
            }
        }
    }
}

