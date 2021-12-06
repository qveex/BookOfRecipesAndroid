package com.example.bookofrecipes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.RecipeItem

@Composable
fun FavoritesScreen(navController: NavController, viewModel: RecipeViewModel) {

    val favsId = viewModel.favoritesId().collectAsState(initial = emptyList())
    val favs = viewModel.favorites(favsId.value).collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ) {
        Column() {
            
            TitleRowTextCenter(text = "Favorite recipes:")
            
            LazyColumn(
                contentPadding = PaddingValues(all = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(items = favs.value) { recipe ->
                    RecipeItem(recipe = recipe, navController = navController)
                }

            }   
        }
    }
}