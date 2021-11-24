package com.example.bookofrecipes.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.widgets.nav.Screen
import kotlin.random.Random

@Composable
fun AddDishScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var dishName by remember { mutableStateOf("") }
            var dishInfo by remember { mutableStateOf("") }

            TitleRowTextCenter(text = "Create your own dish!")

            OutlinedTextField(
                modifier = Modifier
                    .padding(42.dp, 16.dp, 42.dp, 16.dp)
                    .fillMaxWidth(),
                value = dishName,
                onValueChange = { dishName = it },
                label = { Text("Dish name*") },
                singleLine = true

            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = dishInfo,
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                    }
                },
                onValueChange = { dishInfo = it },
                label = { Text("Info") },
                maxLines = 7

            )

            DishDivider()

            var recipeTime by remember { mutableStateOf("") }
            var recipeCost by remember { mutableStateOf("") }
            var complexity by remember { mutableStateOf("") }
            var recipeSpicy by remember { mutableStateOf("") }
            var recipeCuisine by remember { mutableStateOf("") }
            var recipeName by remember { mutableStateOf("") }

            Row() {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(12.dp)
                        .width(80.dp),
                    value = recipeTime,
                    onValueChange = { recipeTime = it },
                    label = { Text("Time") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(12.dp)
                        .width(80.dp),
                    value = recipeCost,
                    onValueChange = { recipeCost = it },
                    label = { Text("Cost") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )

                )


                OutlinedTextField(
                    modifier = Modifier
                        .padding(12.dp)
                        .width(80.dp),
                    value = complexity,
                    onValueChange = { complexity = it },
                    label = { Text("Diff") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )

                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(12.dp)
                        .width(80.dp),
                    value = recipeSpicy,
                    onValueChange = { recipeSpicy = it },
                    label = { Text("Spicy") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = MaterialTheme.shapes.large

                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .padding(42.dp, 16.dp, 42.dp, 16.dp)
                    .fillMaxWidth(),
                value = recipeCuisine,
                onValueChange = { recipeCuisine = it },
                label = { Text("Cuisine") },
                singleLine = true

            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(42.dp, 16.dp, 42.dp, 16.dp)
                    .fillMaxWidth(),
                value = recipeName,
                onValueChange = { recipeName = it },
                label = { Text("Recipe name*") },
                singleLine = true

            )

            OutlinedButton(
                modifier = Modifier.background(Color.DarkGray),
                onClick = {
                    BookOfRecipes.addDish(
                        Dish.build(
                            recipes = mutableListOf<Recipe>(
                                Recipe.build(
                                    name = recipeName,
                                    steps = mutableListOf(),
                                    ingredients = mutableListOf(),

                                    ) { cuisine = recipeCuisine }.apply { recipeId = Random.nextInt(0, 10000) }
                            ),
                            name = recipeName
                        ) {}.apply { dishId = BookOfRecipes.getAllDishes().size }
                    )
                    navController.navigate(Screen.Dishes.route)
                },
                border = BorderStroke(1.dp, Color.White),

                ) {
                Text(text = "Create!")
            }

        }
    }
}


@Composable
@Preview
fun PreviewAddDish() {
    AddDishScreen(rememberNavController())
}