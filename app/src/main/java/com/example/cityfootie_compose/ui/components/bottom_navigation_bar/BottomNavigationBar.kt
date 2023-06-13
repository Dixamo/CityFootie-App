package com.example.cityfootie_compose.ui.components.bottom_navigation_bar

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(
    navMethod: () -> Unit,
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
                        navMethod()
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