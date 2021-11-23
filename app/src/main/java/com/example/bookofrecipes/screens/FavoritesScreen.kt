package com.example.bookofrecipes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.widgets.DishItem
import com.example.bookofrecipes.widgets.RecipeItem

@Composable
fun FavoritesScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 50.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(items = BookOfRecipes.getFavorites()) { recipe ->
                RecipeItem(recipe = recipe, navController = navController)
            }
        }
    }
}