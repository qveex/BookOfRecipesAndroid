package com.example.bookofrecipes.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.nav.BottomBar
import com.example.bookofrecipes.widgets.nav.BottomNavGraph

@Composable
fun MainScreen(recipeViewModel: RecipeViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController, viewModel = recipeViewModel)
    }
}


