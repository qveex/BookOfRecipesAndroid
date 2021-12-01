package com.example.bookofrecipes.widgets.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector

const val DISH_ARGUMENT_KEY = "dishId"
const val RECIPE_ARGUMENT_KEY = "recipeId"


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

    object AddRecipe: Screen(
        route = "addRecipe/{$DISH_ARGUMENT_KEY}",
        title = "AddRecipe",
        icon = Icons.Default.Add
    ) {
        fun passId(dishId: Int) = "addRecipe/$dishId"
    }

    object Dish: Screen(
        route = "dish/{$DISH_ARGUMENT_KEY}",
        title = "Dish",
        icon = Icons.Default.Warning
    ) {
        fun passId(dishId: Int) = "dish/$dishId"
    }

    object Recipe: Screen(
        route = "recipe/{$RECIPE_ARGUMENT_KEY}",
        title = "Recipe",
        icon = Icons.Default.Warning
    ) {
        fun passId(recipeId: Int) = "recipe/$recipeId"
    }
}