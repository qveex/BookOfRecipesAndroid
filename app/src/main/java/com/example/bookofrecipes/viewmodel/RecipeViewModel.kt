package com.example.bookofrecipes.viewmodel

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

    // Состояние поискового виджета (открыт / закрыт)
    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    // Состояние текста в поисковой строке
    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    // обновление состояний
    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }



    // получить все рецепты блюда
    fun recipes(dishId: Int): Flow<List<Recipe>> = repository.getRecipes(dishId)

    // получить все блюда по поисковой строке (или без)
    fun dishes(text: String): Flow<List<Dish>> = repository.getAllDishes(text)

    // получить конерктное блюдо
    fun dish(dishId: Int): Flow<Dish> = repository.getDish(dishId)

    fun recipe(recipeId: Int): Flow<Recipe> = repository.getRecipe(recipeId)

    fun recipeTime(recipeId: Int): Flow<Int> = repository.getRecipeTime(recipeId)

    fun steps(recipeId: Int): Flow<List<CookingStep>> = repository.getSteps(recipeId)

    fun stepsCount(recipeId: Int): Int = repository.getStepsCount(recipeId)

    fun ingredients(text: String): Flow<List<IngredientEntity>> = repository.getIngredients(text)

    fun recipeIngredients(recipeId: Int): Flow<List<IngredientEntity>> = repository.getRecipeIngs(recipeId)

    fun recipeNotIngredients(recipeId: Int): Flow<List<IngredientEntity>> = repository.getNotRecipeIngs(recipeId)

    fun favorites(favs: List<Int>): Flow<List<Recipe>> = repository.getFavorites(favs)

    fun favoritesId(): Flow<List<Int>> = repository.getFavsId()







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
                val v = repository.getIngId(ing.name)
                val ingId = if (v == null) {
                    repository.insertIngredient(IngredientEntity(ing.name))
                    repository.getLastInsertIng()
                } else v
                repository.insertRecIngRef(
                    RecipeIngredientCrossRef(
                        recipeId,
                        0,
                        ing.number,
                        ing.measure
                    )
                )
            }
        }
    }

    fun insertRecipeIngredient(ingredient: IngredientEntity, recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecIngRef(RecipeIngredientCrossRef(
                recipeId,
                ingredient.ingredientId,
                1,
                ""
            ))
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
            step.number = repository.getStepsCount(recipeId) + 1
            repository.insertStep(step)
        }
    }

    // добавление блюдо вместе со всеми входящими в него компонентами
    fun insertDishWithAll(dish: Dish) {
        // новый поток
        viewModelScope.launch(Dispatchers.IO) {

            // добавление блюда
            repository.insertDish(dish)
            // для всех рецептов блюда
            dish.getRecipes().forEach { recipe ->
                // прикрпеляем рецепт к блюду и добавляем в базу
                recipe.dishId = repository.getLastInsertDish()
                repository.insertRecipe(recipe)
                // для каждого ингредиента
                recipe.getIngredients().forEach { ing ->
                    // добавляем ингредиент в БД (Если его нет)
                    repository.insertIngredient(IngredientEntity(ing.name))
                    // закрепляем ингредиент за рецептом
                    repository.insertRecIngRef(
                        RecipeIngredientCrossRef(
                            repository.getLastInsertRecipe(),
                            repository.getLastInsertIng(),
                            ing.number,
                            ing.measure
                        )
                    )
                }
                // закреплячем и добавляем шаги рецепта
                val steps =
                    recipe.getSteps().onEach { it.recipeId = repository.getLastInsertRecipe() }
                repository.insertSteps(steps)

            }
        }
    }



    fun insertFavorite(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(Favorite(recipeId))
        }
    }


    fun deleteDish(dishId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDish(dishId)
        }
    }

    fun deleteRecipe(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecipe(recipeId)
        }
    }

    fun deleteFavorite(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(recipeId)
        }
    }

    fun deleteStep(stepId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStep(stepId)
        }
    }

    fun deleteRecipeIngredient(ingredient: IngredientEntity, recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecipeIngredient(RecipeIngredientCrossRef(
                recipeId,
                ingredient.ingredientId,
                1,
                ""
            ))
        }
    }

}
