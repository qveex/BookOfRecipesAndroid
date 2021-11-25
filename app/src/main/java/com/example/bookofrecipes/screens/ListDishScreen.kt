package com.example.bookofrecipes.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.DishList
import com.example.bookofrecipes.widgets.MainAppBar
import com.example.bookofrecipes.widgets.others.SearchWidgetState

@Composable
fun ListDishScreen(recipeViewModel: RecipeViewModel, navController: NavController) {

    val searchWidgetState by recipeViewModel.searchWidgetState
    val searchTextState by recipeViewModel.searchTextState

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    recipeViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    recipeViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    //Log.i("Search Text", it)
                },
                onSearchTriggered = {
                    recipeViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                },
                title = "Dishes"
            )
        },
        bottomBar = {},
        backgroundColor = Color.DarkGray,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp)
    ) {

        DishList(
            dishes = BookOfRecipes.findDishes(recipeViewModel.searchTextState.value),
            navController = navController
        )

    }
}

