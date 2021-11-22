package com.example.bookofrecipes.widgets.others

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Dishes: BottomBarScreen(
        route = "dishes",
        title = "Dishes",
        icon = Icons.Default.List
    )

    object Favorites: BottomBarScreen(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.FavoriteBorder
    )

    object AddDish: BottomBarScreen(
        route = "addDish",
        title = "AddDish",
        icon = Icons.Default.AddCircle
    )
}