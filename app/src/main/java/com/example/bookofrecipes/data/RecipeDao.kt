package com.example.bookofrecipes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bookofrecipes.models.*
import kotlinx.coroutines.flow.Flow

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
    @Query("SELECT * FROM Dish WHERE name LIKE '%' || :text || '%'")
    fun getDishes(text: String?): Flow<List<Dish>>

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
    @Query("SELECT * FROM CookingStep WHERE recipeId = :recipeId")
    fun getSteps(recipeId: Int): LiveData<List<CookingStep>>

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
    suspend fun insertStep(step: CookingStep)

    @Insert
    suspend fun insertSteps(steps: List<CookingStep>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredient: IngredientEntity): Long

    @Insert
    suspend fun insertRecipeIngredientCrossRef(ref: RecipeIngredientCrossRef)

}