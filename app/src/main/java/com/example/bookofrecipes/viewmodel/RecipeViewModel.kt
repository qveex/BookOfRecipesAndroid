package com.example.bookofrecipes.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookofrecipes.models.*
import com.example.bookofrecipes.repositories.Repository
import com.example.bookofrecipes.widgets.others.SearchWidgetState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {


    // States for search widget in DishListScreen
    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }


    val dishes = repository.getAllDishes()
    val ingredients = repository.getIngredients()
    val recipes = repository.getRecipes()

    private var lastInsertDish = 0
    private var lastInsertRecipe = 0

    fun insertDish(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            lastInsertDish = repository.insertDish(dish).toInt()
        }
    }

    fun insertIngredients(ingredients: List<IngredientEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertIngredients(ingredients)
        }
    }

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipe.dishId = lastInsertDish
            lastInsertRecipe = repository.insertRecipe(recipe).toInt()
        }
    }

    fun insertSteps(steps: List<CookingStep>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSteps(steps)
        }
    }

    fun insertAll(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {

            val dishId = repository.insertDish(dish).toInt()

        }
    }

}
