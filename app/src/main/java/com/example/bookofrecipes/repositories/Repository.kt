package com.example.bookofrecipes.repositories

import androidx.lifecycle.LiveData
import com.example.bookofrecipes.data.RecipeDao
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Ingredient
import com.example.bookofrecipes.models.IngredientEntity
import com.example.bookofrecipes.models.Recipe
import javax.inject.Inject

class Repository @Inject constructor(
    private val recipeDao: RecipeDao
) {

    fun getAllDishes(): LiveData<List<Dish>> = recipeDao.getDishes()

    suspend fun insertDish(dish: Dish) = recipeDao.insertDish(dish)

    suspend fun insertIngredients(ingredients: List<IngredientEntity>) = recipeDao.insertIngredients(ingredients)

    suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe = recipe)

    fun getIngredients(): LiveData<List<IngredientEntity>> = recipeDao.getIngredients()

    fun getRecipes() = recipeDao.getRecipes()

}