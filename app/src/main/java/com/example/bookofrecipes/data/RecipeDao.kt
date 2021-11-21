package com.example.bookofrecipes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookofrecipes.models.*

@Dao
abstract class RecipeDao {

    fun insertDish(dish: Dish) {
        _insertDish(dish)
        dish.getRecipes().forEach { recipe ->

            recipe.dishId = dish.dishId
            recipe.getSteps().forEach {  step -> step.recipeId = recipe.recipeId }


            _insertRecipe(recipe)
            _insertSteps(recipe.getSteps())
            recipe.getIngredients().forEach { ing -> _insertRecipeIngredientCrossRef(RecipeIngredientCrossRef(recipe.recipeId, ing.ingredientId)) }

        }
    }

    @Query("SELECT ingredientId FROM Ingredient WHERE name = :name")
    abstract fun getIngId(name: String): LiveData<Int>

    @Insert
    abstract fun _insertDish(dish: Dish)

    @Insert
    abstract fun _insertRecipe(recipe: Recipe)

    @Insert
    abstract fun _insertSteps(steps: List<CookingStep>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun _insertIngredients(ingredient: List<Ingredient>)

    @Insert
    abstract fun _insertRecipeIngredientCrossRef(ref: RecipeIngredientCrossRef)

    @Transaction
    @Query("SELECT * FROM Recipe")
    abstract fun getRecipesWithIngredients(): LiveData<List<RecipeWithIngredients>>

    @Transaction
    @Query("SELECT * FROM Ingredient")
    abstract fun getIngredientsWithRecipes(): LiveData<List<IngredientWithRecipes>>

    @Transaction
    @Query("SELECT * FROM Dish")
    abstract fun getDishesWithRecipes(): LiveData<List<DishWithRecipes>>

    @Transaction
    @Query("SELECT * FROM Recipe")
    abstract fun getRecipesWithSteps(): LiveData<List<RecipeWithSteps>>

    @Transaction
    @Query("SELECT * FROM Recipe")
    abstract fun getRecipes(): LiveData<List<RecipesWithAllTheOthers>>

}