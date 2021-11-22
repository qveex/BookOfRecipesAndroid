package com.example.bookofrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookofrecipes.screens.MainScreen
import com.example.bookofrecipes.ui.theme.BookOfRecipesTheme
import com.example.bookofrecipes.viewmodel.RecipeViewModel

class MainActivity : ComponentActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BookOfRecipesTheme(true) {

                MainScreen(recipeViewModel = recipeViewModel)
                //Dishes(recipeViewModel)

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




