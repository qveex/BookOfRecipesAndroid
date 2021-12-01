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
import kotlinx.coroutines.flow.Flow
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


    val dishes = repository.getDishes(searchTextState.value)
    val ingredients = repository.getIngredients()
    val recipes = repository.getRecipes()

    fun dishes(text: String) = repository.getDishes(text)
    fun steps(recipeId: Int) = repository.getSteps(recipeId)


    fun insertDish(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDish(dish)
        }
    }

    fun insertIngredient(ingredient: IngredientEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertIngredient(ingredient)
        }
    }

    fun insertRecipeIngredients(ingredients: List<Ingredient>, recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ingredients.forEach { ing ->
                val v = repository.getIngId(ing.name).value
                val ingId = if (v == null) {
                    repository.insertIngredient(IngredientEntity(ing.name))
                    repository.getLastInsertIng()
                } else v
                repository.insertRecIngRef(
                    RecipeIngredientCrossRef(
                        recipeId,
                        0 ,
                        ing.number,
                        ing.measure
                    )
                )
            }
        }
    }

    fun insertRecipe(recipe: Recipe, dishId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            recipe.dishId = dishId
            repository.insertRecipe(recipe)
        }
    }

    fun insertStep(step: CookingStep, recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            step.recipeId = recipeId
            repository.insertStep(step)
        }
    }

    fun insertAll(dish: Dish) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.insertDish(dish)
            dish.getRecipes().forEach { recipe ->
                recipe.dishId = repository.getLastInsertDish()
                repository.insertRecipe(recipe)

                recipe.getIngredients().forEach { ing ->
                    repository.insertIngredient(IngredientEntity(ing.name))
                    repository.insertRecIngRef(RecipeIngredientCrossRef(
                        repository.getLastInsertRecipe(),
                        repository.getLastInsertIng(),
                        ing.number,
                        ing.measure
                    ))
                }

                val steps = recipe.getSteps().onEach { it.recipeId = repository.getLastInsertRecipe() }
                repository.insertSteps(steps)

            }

        }
    }

}
