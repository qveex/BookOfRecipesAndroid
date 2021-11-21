package com.example.bookofrecipes.screens

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.MainAppBar
import com.example.bookofrecipes.widgets.SearchWidgetState

@Composable
fun ListDishScreen(recipeViewModel: RecipeViewModel) {

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
                    Log.i("Search Text", it)
                },
                onSearchTriggered = {
                    recipeViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                },
                title = "Dishes"
            )
        }
    ) {}
}

