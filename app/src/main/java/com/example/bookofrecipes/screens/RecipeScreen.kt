package com.example.bookofrecipes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.ui.theme.Purple700
import com.example.bookofrecipes.ui.theme.lGrey
import com.example.bookofrecipes.widgets.DishAppBar
import com.example.bookofrecipes.widgets.ExpandableCard

@ExperimentalMaterialApi
@Composable
fun RecipeScreen(navController: NavController, recipeId: Int) {

    val recipe = BookOfRecipes.getRecipe(recipeId = recipeId)

    if (recipe != null) {

        Scaffold(
            topBar = { DishAppBar(onDeleteClicked = { /*TODO*/ }, title = recipe.name) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray),
                //contentAlignment = Alignment.Center
            ) {

                Column(
                    Modifier.padding(24.dp)
                ) {
                    ExtendedFloatingActionButton(
                        backgroundColor = Purple700,
                        text = { Text("Star") },
                        onClick = { BookOfRecipes.addFavorite(recipeId = recipeId) },
                        icon = {
                            Icon(
                                imageVector =
                                if (BookOfRecipes.isFav(recipeId = recipeId))
                                    Icons.Filled.Favorite
                                else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite"
                            )
                        },
                    )

                    ExpandableCard(title = "Title", description = "lalalalalalalaalalalalalalalalalalalalalalalalalalalalaalaablblblblblblb")
                }
            }
        }

    }

}