package com.example.bookofrecipes.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Ingredient
import com.example.bookofrecipes.models.IngredientEntity
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.repositories.Repository
import com.example.bookofrecipes.widgets.others.SearchWidgetState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {


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

    fun insertDish(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDish(dish)
        }
    }

    fun insertIngredients(ingredients: List<IngredientEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertIngredients(ingredients)
        }
    }

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecipe(recipe)
        }
    }

    fun getRecipes() = repository.getRecipes()

}
