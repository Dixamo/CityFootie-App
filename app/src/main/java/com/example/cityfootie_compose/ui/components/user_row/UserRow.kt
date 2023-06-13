package com.example.cityfootie_compose.ui.components.user_row

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cityfootie_compose.R
import com.example.cityfootie_compose.model.Player

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
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Text(
            text = player.username!!,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
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