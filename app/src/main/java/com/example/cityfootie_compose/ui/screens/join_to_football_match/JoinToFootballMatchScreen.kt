package com.example.cityfootie_compose.ui.screens.join_to_football_match

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.R
import com.example.cityfootie_compose.model.Player
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
        TopAppBar() {
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
        var isGetCompleted = joinToFootballMatchViewModel.isGetCompleted
        var footballMatch = joinToFootballMatchViewModel.footballMatch

        if (!isGetCompleted) {
            LaunchedEffect(Unit) {
                joinToFootballMatchViewModel.getFootballMatch(latitude.toDouble(), longitude.toDouble())
            }
        }
        var isGetPlayersCompleted = joinToFootballMatchViewModel.isGetPlayersCompleted
        if (!isGetPlayersCompleted) {
            LaunchedEffect(Unit) {
                joinToFootballMatchViewModel.getPlayersByFootballMatch(latitude.toDouble(), longitude.toDouble())
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
                    text = "Usuarios apuntados:${footballMatch.numberPlayers}/${footballMatch.numberMax}" ,
                    color = MaterialTheme.colors.primary,
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )

                Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    var isError: Boolean = joinToFootballMatchViewModel.isError
                    Text(
                        text = "Ya estas apuntado o el partido esta lleno",
                        modifier = Modifier.alpha(isError.toFloat()),
                        color = MaterialTheme.colors.error
                    )
                }

                Button(
                    onClick = {
                        joinToFootballMatchViewModel.putFootballMatch(latitude.toDouble(), longitude.toDouble(), email)
                    },
                ) {
                    Text(text = "Unirse partido")
                }

                var players = joinToFootballMatchViewModel.players
                if (isGetPlayersCompleted) {
                    LazyColumn {
                        if (players != null) {
                            items(players.toList()) {
                                    player -> UserRow(player = player)
                            }
                        }
                    }
                }

                var isPostCompleted = joinToFootballMatchViewModel.isPostCompleted
                var response = joinToFootballMatchViewModel.response
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

@Composable
fun UserRow(player: Player) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .padding(8.dp)
        .clickable {
            //NAVEGACION
        }
    ) {
        UserImage()
        UserInfo(player = player)
    }
}

@Composable
fun UserImage() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Imagen de Perfil",
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
            .size(64.dp)
    )
}

@Composable
fun UserInfo(player: Player) {
    Column(modifier = Modifier
        .padding(start = 8.dp)
    ) {
        Text(
            text = player.username!!,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Text(
                text = player.number.toString(),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle2,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = player.name!!,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}