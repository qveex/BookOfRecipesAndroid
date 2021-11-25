package com.example.bookofrecipes.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.models.CookingStep
import com.example.bookofrecipes.ui.theme.Purple700
import com.example.bookofrecipes.ui.theme.lGrey
import com.example.bookofrecipes.widgets.DishAppBar
import com.example.bookofrecipes.widgets.DishItem
import com.example.bookofrecipes.widgets.ExpandableCard
import com.example.bookofrecipes.widgets.RecipeAppBar
import com.example.bookofrecipes.widgets.nav.Screen

@ExperimentalMaterialApi
@Composable
fun RecipeScreen(navController: NavController, recipeId: Int) {

    val recipe = BookOfRecipes.getRecipe(recipeId = recipeId)

    if (recipe != null) {

        Scaffold(
            topBar = {
                RecipeAppBar(
                    onDeleteClicked = {
                        navController.navigate(Screen.Dishes.route)
                        BookOfRecipes.removeRecipe(recipeId)
                    },
                    onBackClicked = { navController.popBackStack() },
                    onHeartClicked = { BookOfRecipes.addFavorite(recipeId) },
                    title = recipe.name
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray),
                contentAlignment = Alignment.TopCenter,
            ) {

                Column(
                    Modifier.padding(16.dp)
                ) {

                    Row(
                        Modifier.padding(8.dp),
                    ) {
                        Text(
                            text = "Time: ${recipe.cookingTime} min",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Complexity: ${recipe.complexity} stars",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        //Text(text = "Cuisine: ")
                    }

                    Row(
                        Modifier.padding(8.dp)
                    ) {

                        Text(
                            text = "Spicy: ${recipe.spicy} peppers",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Cost: ${recipe.cost} ₽",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    TitleRowTextCenter(text = if (recipe.getSteps().isNotEmpty()) "Рецепт:" else "Рецепт потерялся...")
                    StepsList(steps = recipe.getSteps())

                }
            }
        }

    }

}


@ExperimentalMaterialApi
@Composable
fun StepsList(steps: List<CookingStep>) {
    LazyColumn(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(items = steps) { step ->
            ExpandableCard(title = step.title, description = step.info)
        }
    }
}