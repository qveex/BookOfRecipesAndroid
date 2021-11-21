package com.example.bookofrecipes.repositories

import androidx.lifecycle.LiveData
import com.example.bookofrecipes.data.RecipeDao
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.DishWithRecipes
import com.example.bookofrecipes.models.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val recipeDao: RecipeDao
) {

    fun getAllDishes(): LiveData<List<Dish>> = recipeDao.getDishes()/*{
        val data = recipeDao.getDishesWithRecipes()
        val dishes = mutableListOf<Dish>()

        data.value?.forEach {
            val dish = it.dish.apply { this.addRecipes(it.recipes) }
            dishes.add(dish)
        }

        return LiveData<List<Dish>>(dishes)
    }*/

    suspend fun insertDish(dish: Dish) = recipeDao.insertDish(dish)

}