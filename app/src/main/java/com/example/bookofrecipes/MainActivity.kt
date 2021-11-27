package com.example.bookofrecipes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.screens.MainScreen
import com.example.bookofrecipes.ui.theme.BookOfRecipesTheme
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookOfRecipesTheme(true) {

                MainScreen(recipeViewModel = recipeViewModel)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookOfRecipesTheme {

    }
}




