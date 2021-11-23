package com.example.bookofrecipes.widgets.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookofrecipes.screens.*
import com.example.bookofrecipes.viewmodel.RecipeViewModel

@Composable
fun BottomNavGraph(navController: NavHostController, viewModel: RecipeViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dishes.route
    ) {
        composable(route = Screen.Dishes.route) {
            ListDishScreen(recipeViewModel = viewModel, navController = navController)
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = Screen.AddDish.route) {
            AddDishScreen()
        }
        composable(route = Screen.Dish.route) {
            DishScreen()
        }
        composable(route = Screen.Recipe.route) {
            RecipeScreen()
        }
    }
}