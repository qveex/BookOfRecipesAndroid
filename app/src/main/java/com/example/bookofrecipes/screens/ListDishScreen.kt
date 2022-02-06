package com.example.bookofrecipes.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.DishList
import com.example.bookofrecipes.widgets.MainAppBar
import com.example.bookofrecipes.widgets.others.SearchWidgetState
import kotlinx.coroutines.flow.collect

@Composable
fun ListDishScreen(viewModel: RecipeViewModel, navController: NavController) {

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState
    val dishes by viewModel.dishes(searchTextState).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    //Log.i("Search Text", it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                },
                title = "Dishes"
            )
        },
        bottomBar = {},
        backgroundColor = Color.DarkGray,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp)
    ) {

        DishList(
            dishes = dishes,
            navController = navController
        )

    }
}

