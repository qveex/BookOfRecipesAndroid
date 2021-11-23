package com.example.bookofrecipes.widgets.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookofrecipes.screens.*
import com.example.bookofrecipes.viewmodel.RecipeViewModel

@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController, viewModel: RecipeViewModel) {
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
        composable(
            route = Screen.Dish.route,
            arguments = listOf(navArgument("dishId"){
                type = NavType.IntType
            })
        ) {
            DishScreen(navController = navController, it.arguments!!.getInt("dishId"))
        }
        composable(route = Screen.Recipe.route) {
            RecipeScreen()
        }
    }
}