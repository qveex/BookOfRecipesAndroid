package com.example.bookofrecipes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookofrecipes.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM IngredientEntity")
    fun getIngredients(): Flow<List<IngredientEntity>>

    @Transaction
    @Query("SELECT ingredientId FROM IngredientEntity WHERE name = :name")
    fun getIngId(name: String): Flow<Int>

    @Transaction
    @Query("SELECT * FROM Dish WHERE name = :name")
    fun getDishesByName(name: String): Flow<List<Dish>>

    @Transaction
    @Query("SELECT * FROM Dish WHERE name LIKE '%' || :text || '%'")
    fun getDishes(text: String?): Flow<List<Dish>>

    @Transaction
    @Query("SELECT * FROM Dish WHERE dishId = :dishId")
    fun getDish(dishId: Int): Flow<Dish>

    @Transaction
    @Query("SELECT * FROM Recipe WHERE dishId = :dishId")
    fun getRecipes(dishId: Int): Flow<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM Recipe WHERE dishId = :dishId and cuisine = :cuisine")
    fun getCuisineRecipes(dishId: Int, cuisine: String): Flow<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM Recipe WHERE recipeId = :recipeId")
    fun getRecipe(recipeId: Int): Flow<Recipe>

    @Transaction
    @Query("SELECT * FROM CookingStep WHERE recipeId = :recipeId")
    fun getSteps(recipeId: Int): Flow<List<CookingStep>>

    @Transaction
    @Query("SELECT * FROM CookingStep")
    fun getAllSteps(): LiveData<List<CookingStep>>

    @Transaction
    @Query("SELECT * FROM RecipeIngredientCrossRef WHERE recipeId = :recipeId")
    fun getRecipeIngredientsRef(recipeId: Int): Flow<List<RecipeIngredientCrossRef>>

    @Transaction
    @Query("SELECT name FROM IngredientEntity WHERE ingredientId IN (:ingsId)")
    fun getRecipeIngredients(ingsId: List<Int>): Flow<List<String>>










    @Insert
    suspend fun insertDish(dish: Dish): Long

    @Insert
    suspend fun insertFavorite(fav: Favorite)

    @Insert
    suspend fun insertRecipe(recipe: Recipe): Long

    @Insert
    suspend fun insertStep(step: CookingStep)

    @Insert
    suspend fun insertSteps(steps: List<CookingStep>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredient: IngredientEntity): Long

    @Insert
    suspend fun insertRecipeIngredientCrossRef(ref: RecipeIngredientCrossRef)

}