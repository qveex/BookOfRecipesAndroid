package com.example.bookofrecipes.widgets.others

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookofrecipes.screens.AddDishScreen
import com.example.bookofrecipes.screens.FavoritesScreen
import com.example.bookofrecipes.screens.ListDishScreen
import com.example.bookofrecipes.viewmodel.RecipeViewModel

@Composable
fun BottomNavGraph(navController: NavHostController, viewModel: RecipeViewModel) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Dishes.route
    ) {
        composable(route = BottomBarScreen.Dishes.route) {
            ListDishScreen(recipeViewModel = viewModel)
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = BottomBarScreen.AddDish.route) {
            AddDishScreen()
        }
    }
}