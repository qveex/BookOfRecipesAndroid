package com.example.bookofrecipes.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.nav.Screen

@Composable
fun AddRecipeScreen(
    recipeViewModel: RecipeViewModel,
    navController: NavController,
    dishId: Int
) {
    Scaffold(

    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var recipeTime by remember { mutableStateOf("0") }
                var recipeCost by remember { mutableStateOf("0") }
                var recipeComplexity by remember { mutableStateOf("0") }
                var recipeSpicy by remember { mutableStateOf("0") }
                var recipeCuisine by remember { mutableStateOf("") }
                var recipeName by remember { mutableStateOf("") }

                TitleRowTextCenter(text = "Create recipe!")

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
                        value = recipeComplexity,
                        onValueChange = { recipeComplexity = it },
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

                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    onClick = {
                        navController.popBackStack()
                    },
                    border = BorderStroke(1.dp, Color.White),

                    ) {
                    Text(text = "Create!")
                }

            }

        }
    }


}

@Composable
@Preview
fun AddRecipeScreenPreview() {
    AddRecipeScreen(viewModel(), rememberNavController(), 0)
}