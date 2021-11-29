package com.example.bookofrecipes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookofrecipes.models.*

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM IngredientEntity")
    fun getIngredients(): LiveData<List<IngredientEntity>>

    @Transaction
    @Query("SELECT ingredientId FROM IngredientEntity WHERE name = :name")
    fun getIngId(name: String): LiveData<Int>

    @Transaction
    @Query("SELECT * FROM Dish WHERE name = :name")
    fun getDishesByName(name: String): LiveData<List<Dish>>

    @Transaction
    @Query("SELECT * FROM Dish")
    fun getDishes(): LiveData<List<Dish>>

    @Transaction
    @Query("SELECT * FROM Dish WHERE dishId = :dishId")
    fun getDish(dishId: Int): LiveData<Dish>

    @Transaction
    @Query("SELECT * FROM Recipe")
    fun getRecipes(): LiveData<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM Recipe WHERE recipeId = :recipeId")
    fun getRecipe(recipeId: Int): LiveData<Recipe>

    @Transaction
    @Query("SELECT * FROM CookingStep")
    fun getSteps(): LiveData<List<CookingStep>>

    @Transaction
    @Query("SELECT * FROM RecipeIngredientCrossRef WHERE recipeId = :recipeId")
    fun getRecipeIngredientsRef(recipeId: Int): LiveData<List<RecipeIngredientCrossRef>>

    @Transaction
    @Query("SELECT name FROM IngredientEntity WHERE ingredientId IN (:ingsId)")
    fun getRecipeIngredients(ingsId: List<Int>): LiveData<List<String>>










    @Insert
    suspend fun insertDish(dish: Dish): Long

    @Insert
    suspend fun insertFavorite(fav: Favorite)

    @Insert
    suspend fun insertRecipe(recipe: Recipe): Long

    @Insert
    suspend fun insertSteps(steps: List<CookingStep>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredient: List<IngredientEntity>)

    @Insert
    suspend fun insertRecipeIngredientCrossRef(ref: RecipeIngredientCrossRef)

}