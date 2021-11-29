package com.example.bookofrecipes.repositories

import androidx.lifecycle.LiveData
import com.example.bookofrecipes.data.RecipeDao
import com.example.bookofrecipes.models.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val recipeDao: RecipeDao
) {

    fun getAllDishes(): LiveData<List<Dish>> = recipeDao.getDishes()

    fun getIngredients(): LiveData<List<IngredientEntity>> = recipeDao.getIngredients()

    fun getRecipes() = recipeDao.getRecipes()

    suspend fun insertDish(dish: Dish) = recipeDao.insertDish(dish)

    suspend fun insertIngredients(ingredients: List<IngredientEntity>) = recipeDao.insertIngredients(ingredients)

    suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe)

    suspend fun insertFavorite(favorite: Favorite) = recipeDao.insertFavorite(favorite)

    suspend fun insertRecIngRef(ref: RecipeIngredientCrossRef) = recipeDao.insertRecipeIngredientCrossRef(ref)

    suspend fun insertSteps(steps: List<CookingStep>) = recipeDao.insertSteps(steps)

}