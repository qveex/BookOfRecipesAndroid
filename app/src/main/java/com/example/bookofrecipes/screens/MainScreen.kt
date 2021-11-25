package com.example.bookofrecipes.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.nav.BottomBar
import com.example.bookofrecipes.widgets.nav.NavGraph

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(recipeViewModel: RecipeViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavGraph(navController = navController, viewModel = recipeViewModel)
    }
}


