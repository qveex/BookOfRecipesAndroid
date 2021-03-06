package com.example.bookofrecipes.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.models.CookingStep
import com.example.bookofrecipes.models.IngredientEntity
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.*
import com.example.bookofrecipes.widgets.nav.Screen

@ExperimentalMaterialApi
@Composable
fun RecipeScreen(navController: NavController, viewModel: RecipeViewModel, recipeId: Int) {

    val recipe by viewModel.recipe(recipeId).collectAsState(initial = null)
    val recipeTime by viewModel.recipeTime(recipeId).collectAsState(initial = 0)
    val steps by viewModel.steps(recipeId).collectAsState(initial = emptyList())
    val allIngs by viewModel.recipeNotIngredients(recipeId).collectAsState(initial = emptyList())
    val ings by viewModel.recipeIngredients(recipeId).collectAsState(initial = emptyList())
    val favoritesId by viewModel.favoritesId().collectAsState(initial = emptyList())

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
        topBar = {
            RecipeAppBar(
                onDeleteClicked = {
                    navController.navigate(Screen.Dishes.route)
                    viewModel.deleteRecipe(recipeId)
                },
                onBackClicked = { navController.popBackStack() },
                onHeartClicked = {
                    if (!favoritesId.contains(recipeId)) viewModel.insertFavorite(recipeId)
                    else viewModel.deleteFavorite(recipeId)
                },
                title = recipe?.name ?: "null"
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter,
        ) {

            Column(
                Modifier.padding(8.dp)
            ) {

                TitleRowTextCenter(
                    text = recipe?.name ?: "null",
                    onClicked = {
                        Toast.makeText(
                            context,
                            "calories: ${recipe?.energyValue?.calorie}\n" +
                                    "proteins: ${recipe?.energyValue?.proteins}\n" +
                                    "fats: ${recipe?.energyValue?.fats}\n" +
                                    "carbohydrates: ${recipe?.energyValue?.carbohydrates}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
                
                Row(
                    Modifier.padding(8.dp),
                ) {
                    Text(
                        text = "Time: $recipeTime min",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Complexity: ${recipe?.complexity} stars",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    Modifier.padding(8.dp)
                ) {

                    Text(
                        text = "Spicy: ${recipe?.spicy} peppers",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Cost: ${recipe?.cost} ???",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                }

                IngDropDownMenu(
                    allIngs,
                    "Ingredients",
                    onClicked = fun (ing: IngredientEntity) {
                        viewModel.insertRecipeIngredient(
                            ing,
                            recipeId
                        )
                    },
                    onAddClicked = fun (ing: IngredientEntity) {
                        viewModel.insertIngredient(ing)
                    }
                )
                IngList(
                    ings = ings,
                    onClicked = fun(ing: IngredientEntity) {
                        viewModel.deleteRecipeIngredient(
                            ing,
                            recipeId
                        )
                    }
                )

                if (steps.isEmpty()) {
                    TitleRowTextCenter(text = "Recipe lost...")
                    RowTextCenter(text = "Create your own!")
                } else {
                    TitleRowTextCenter(text = "Recipe:")
                    steps.forEach { step ->
                        ExpandableCard(
                            title = step.title,
                            description = step.info,
                            time = step.time,
                            onDeleteClicked = { viewModel.deleteStep(step.stepId) }
                        )
                    }
                }
                ExpandableTextFieldCard(
                    recipeId = recipeId,
                    onAddClicked = fun(step: CookingStep, recipeId: Int) {
                        viewModel.insertStep(
                            step,
                            recipeId
                        )
                    }
                )
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun StepsList(steps: List<CookingStep>) {
    LazyColumn(
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = steps) { step ->
            ExpandableCard(title = step.title, description = step.info, time = step.time, onDeleteClicked = { })
        }
    }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun RecipeScreenPreview() {
    //RecipeScreen(navController = rememberNavController(), , recipeId = 4)
}