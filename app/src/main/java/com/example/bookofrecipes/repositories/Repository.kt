package com.example.bookofrecipes.repositories

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





    fun getAllDishes(text: String?) = recipeDao.getDishes(text)

    fun getDish(dishId: Int) = recipeDao.getDish(dishId)

    fun getRecipe(recipeId: Int) = recipeDao.getRecipe(recipeId)

    fun getIngredients() = recipeDao.getIngredients()

    fun getAllRecipes() = recipeDao.getAllRecipes()

    fun getRecipes(dishId: Int) = recipeDao.getRecipes(dishId)

    fun getSteps(recipeId: Int) = recipeDao.getSteps(recipeId = recipeId)

    fun getStepsCount(recipeId: Int) = recipeDao.getStepsCount(recipeId)

    fun getRecipeTime(recipeId: Int) = recipeDao.getRecipeTime(recipeId)

    fun getFavorites(favs: List<Int>) = recipeDao.getFavorites(favs)

    fun getFavsId() = recipeDao.getFavId()

    fun getIngId(name: String) = recipeDao.getIngId(name)








    suspend fun insertDish(dish: Dish) { lastInsertDish = recipeDao.insertDish(dish).toInt() }

    suspend fun insertIngredient(ingredient: IngredientEntity) { lastInsertIng = recipeDao.insertIngredient(ingredient).toInt() }

    suspend fun insertRecipe(recipe: Recipe) { lastInsertRecipe = recipeDao.insertRecipe(recipe).toInt() }

    suspend fun insertFavorite(favorite: Favorite) = recipeDao.insertFavorite(favorite)

    suspend fun insertRecIngRef(ref: RecipeIngredientCrossRef) = recipeDao.insertRecipeIngredientCrossRef(ref)

    suspend fun insertStep(step: CookingStep) = recipeDao.insertStep(step)

    suspend fun insertSteps(steps: List<CookingStep>) = recipeDao.insertSteps(steps)








    suspend fun deleteDish(dishId: Int) = recipeDao.deleteDish(dishId)

    suspend fun deleteRecipe(recipeId: Int) = recipeDao.deleteRecipe(recipeId)

    suspend fun deleteFavorite(recipeId: Int) = recipeDao.deleteFavorite(recipeId)

    suspend fun deleteStep(stepId: Int) = recipeDao.deleteStep(stepId)
}