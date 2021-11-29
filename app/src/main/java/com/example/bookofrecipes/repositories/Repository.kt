package com.example.bookofrecipes.repositories

import androidx.lifecycle.LiveData
import com.example.bookofrecipes.data.RecipeDao
import com.example.bookofrecipes.models.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val recipeDao: RecipeDao
) {

    private var lastInsertDish = 0
    private var lastInsertRecipe = 0
    private var lastInsertIng = 0
    fun getLastInsertDish() = lastInsertDish
    fun getLastInsertRecipe() = lastInsertRecipe
    fun getLastInsertIng() = lastInsertIng



    fun getAllDishes(): LiveData<List<Dish>> = recipeDao.getDishes()

    fun getIngredients(): LiveData<List<IngredientEntity>> = recipeDao.getIngredients()

    fun getRecipes() = recipeDao.getRecipes()

    fun getSteps(recipeId: Int) = recipeDao.getSteps(recipeId = recipeId)

    suspend fun insertDish(dish: Dish) {
        lastInsertDish = recipeDao.insertDish(dish).toInt()
    }

    suspend fun insertIngredient(ingredient: IngredientEntity) {
        lastInsertIng = recipeDao.insertIngredient(ingredient).toInt()
    }

    suspend fun insertRecipe(recipe: Recipe) {
        lastInsertRecipe = recipeDao.insertRecipe(recipe).toInt()
    }

    suspend fun insertFavorite(favorite: Favorite) = recipeDao.insertFavorite(favorite)

    suspend fun insertRecIngRef(ref: RecipeIngredientCrossRef) =
        recipeDao.insertRecipeIngredientCrossRef(ref)

    suspend fun insertStep(step: CookingStep) = recipeDao.insertStep(step)

    suspend fun insertSteps(steps: List<CookingStep>) = recipeDao.insertSteps(steps)
}