package com.example.bookofrecipes.widgets.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bookofrecipes.screens.*
import com.example.bookofrecipes.viewmodel.RecipeViewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController, viewModel: RecipeViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dishes.route
    ) {

        composable(route = Screen.Dishes.route) {
            ListDishScreen(viewModel, navController = navController)
        }

        composable(route = Screen.Favorites.route) {
            FavoritesScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = Screen.AddDish.route) {
            AddDishScreen(navController = navController, viewModel = viewModel)
        }



        composable(
            route = Screen.Dish.route,
            arguments = listOf(navArgument("dishId") {
                type = NavType.IntType
            })
        ) {
            DishScreen(navController = navController, viewModel, it.arguments!!.getInt("dishId"))
        }

        composable(
            route = Screen.AddRecipe.route,
            arguments = listOf(navArgument("dishId") {
                type = NavType.IntType
            })
        ) {
            AddRecipeScreen(viewModel, navController = navController, it.arguments!!.getInt("dishId"))
        }

        composable(
            route = Screen.Recipe.route,
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) {
            RecipeScreen(navController, viewModel, it.arguments!!.getInt("recipeId"))
        }
    }
}