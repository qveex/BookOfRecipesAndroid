package com.example.bookofrecipes.widgets.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Dishes: Screen(
        route = "dishes",
        title = "Dishes",
        icon = Icons.Default.List
    )

    object Favorites: Screen(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.FavoriteBorder
    )

    object AddDish: Screen(
        route = "addDish",
        title = "AddDish",
        icon = Icons.Default.Add
    )

    object Dish: Screen(
        route = "dish",
        title = "Dish",
        icon = Icons.Default.Warning
    )

    object Recipe: Screen(
        route = "recipe",
        title = "Recipe",
        icon = Icons.Default.Warning
    )
}