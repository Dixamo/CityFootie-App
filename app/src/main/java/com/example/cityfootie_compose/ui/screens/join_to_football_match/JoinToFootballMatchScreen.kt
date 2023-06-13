package com.example.cityfootie_compose.ui.screens.join_to_football_match

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.components.user_row.UserRow
import com.example.cityfootie_compose.util.toFloat


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun JoinToFootballMatchScreen(
    email: String,
    latitude: String,
    longitude: String,
    goBack: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    goBack()
                })
        }
    }) {
        BodyContent(email, latitude, longitude, goBack)
    }
}

@Composable
fun BodyContent(
    email: String,
    latitude: String,
    longitude: String,
    goBack: () -> Unit,
    joinToFootballMatchViewModel: JoinToFootballMatchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val isGetCompleted = joinToFootballMatchViewModel.isGetCompleted
        val footballMatch = joinToFootballMatchViewModel.footballMatch

        val isGetPlayersCompleted = joinToFootballMatchViewModel.isGetPlayersCompleted
        val isError: Boolean = joinToFootballMatchViewModel.isError
        val players = joinToFootballMatchViewModel.players

        val isPostCompleted = joinToFootballMatchViewModel.isPostCompleted
        val response = joinToFootballMatchViewModel.response

        if (!isGetCompleted) {
            LaunchedEffect(Unit) {
                joinToFootballMatchViewModel.getFootballMatch(
                    latitude.toDouble(),
                    longitude.toDouble()
                )
            }
        }

        if (!isGetPlayersCompleted) {
            LaunchedEffect(Unit) {
                joinToFootballMatchViewModel.getPlayersByFootballMatch(
                    latitude.toDouble(),
                    longitude.toDouble()
                )
            }
        }

        if (isGetCompleted) {
            if (footballMatch != null) {
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    "Unirse al Partido",
                    color = MaterialTheme.colors.primary,
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Usuarios apuntados:${footballMatch.numberPlayers}/${footballMatch.numberMax}",
                    color = MaterialTheme.colors.primary,
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )

                Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Ya estas apuntado o el partido esta lleno",
                        modifier = Modifier.alpha(isError.toFloat()),
                        color = MaterialTheme.colors.error
                    )
                }

                Text(
                    text = "Fecha y hora: ${footballMatch.date}",
                    color = MaterialTheme.colors.primary,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )

                Button(
                    onClick = {
                        joinToFootballMatchViewModel.putFootballMatch(
                            latitude.toDouble(),
                            longitude.toDouble(),
                            email
                        )
                    },
                ) {
                    Text(text = "Unirse partido")
                }

                if (isGetPlayersCompleted) {
                    LazyColumn {
                        if (players != null) {
                            items(players.toList()) { player ->
                                UserRow(player = player)
                            }
                        }
                    }
                }

                LaunchedEffect(response) {
                    if (response != null) {
                        if (isPostCompleted) {
                            goBack()
                        }
                    }
                }
            }
        }
    }
}