package com.example.bookofrecipes.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.models.Description
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.nav.Screen

@Composable
fun AddRecipeScreen(
    viewModel: RecipeViewModel,
    navController: NavController,
    dishId: Int
) {

    val context = LocalContext.current
    val MAX_BYTE_LENGTH = 1
    val MAX_INT_LENGTH = 3

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

                var recipeTime by remember { mutableStateOf("") }
                var recipeCost by remember { mutableStateOf("") }
                var recipeComplexity by remember { mutableStateOf("") }
                var recipeSpicy by remember { mutableStateOf("") }
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
                        onValueChange = {
                            if (it.length <= MAX_INT_LENGTH && it.isDigitsOnly())
                                if (it.toInt() >= 0)
                                    recipeTime = it
                            else
                                Toast.makeText(context, "fields is not correct!", Toast.LENGTH_SHORT)
                                    .show()
                        },
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
                        onValueChange = {
                            if (it.length <= MAX_INT_LENGTH && it.isDigitsOnly())
                                if (it.toInt() >= 0)
                                    recipeCost = it
                            else
                                Toast.makeText(context, "fields is not correct!", Toast.LENGTH_SHORT)
                                    .show()
                        },
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
                        onValueChange = {
                            if (it.length <= MAX_BYTE_LENGTH && it.isDigitsOnly())
                                if (it.toByte() in 0..5)
                                    recipeComplexity = it
                            else
                                Toast.makeText(context, "fields is not correct!", Toast.LENGTH_SHORT)
                                    .show()
                        },
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
                        onValueChange = {
                            if (it.length <= MAX_BYTE_LENGTH && it.isDigitsOnly())
                                if (it.toByte() in 0..5)
                                    recipeSpicy = it
                            else
                                Toast.makeText(context, "fields is not correct!", Toast.LENGTH_SHORT)
                                    .show()
                        },
                        label = { Text("Spicy") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        shape = MaterialTheme.shapes.large

                    )
                }

                OutlinedAddButton(
                    text = "create!",
                    onClicked = {
                        viewModel.insertRecipe(
                            Recipe(
                                Description(.0, .0, .0, .0),
                                recipeName,
                                recipeTime.toInt(),
                                recipeCuisine,
                                recipeCost.toInt(),
                                recipeComplexity.toByte(),
                                recipeSpicy.toByte()
                            ),
                            dishId = dishId
                        )
                        Toast.makeText(context, "Recipe was created!", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Dish.passId(dishId))
                    }
                )

            }

        }
    }


}

@Composable
fun OutlinedAddButton(text: String, onClicked: () -> Unit) {
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        onClick = onClicked,
        border = BorderStroke(1.dp, Color.White),

        ) {
        Text(text = text)
    }
}

@Composable
@Preview
fun AddRecipeScreenPreview() {
    AddRecipeScreen(viewModel(), rememberNavController(), 0)
}
