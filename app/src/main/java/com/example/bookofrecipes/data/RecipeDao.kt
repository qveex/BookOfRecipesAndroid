package com.example.bookofrecipes.data

import androidx.room.*
import com.example.bookofrecipes.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM IngredientEntity")
    fun getIngredients(): Flow<List<IngredientEntity>>

    @Query("SELECT ingredientId FROM IngredientEntity WHERE name = :name")
    fun getIngId(name: String): Flow<Int>

    @Query("SELECT * FROM Dish WHERE name = :name")
    fun getDishesByName(name: String): Flow<List<Dish>>

    @Query("SELECT * FROM Dish WHERE name LIKE '%' || :text || '%'")
    fun getDishes(text: String?): Flow<List<Dish>>

    @Query("SELECT * FROM Dish WHERE dishId = :dishId")
    fun getDish(dishId: Int): Flow<Dish>

    @Query("SELECT * FROM Recipe WHERE dishId = :dishId")
    fun getRecipes(dishId: Int): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE dishId = :dishId and cuisine = :cuisine")
    fun getCuisineRecipes(dishId: Int, cuisine: String): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE recipeId = :recipeId")
    fun getRecipe(recipeId: Int): Flow<Recipe>

    @Query("SELECT * FROM CookingStep WHERE recipeId = :recipeId")
    fun getSteps(recipeId: Int): Flow<List<CookingStep>>

    @Query("SELECT * FROM RecipeIngredientCrossRef WHERE recipeId = :recipeId")
    fun getRecipeIngredientsRef(recipeId: Int): Flow<List<RecipeIngredientCrossRef>>

    @Query("SELECT name FROM IngredientEntity WHERE ingredientId IN (:ingsId)")
    fun getRecipeIngredients(ingsId: List<Int>): Flow<List<String>>

    @Query("SELECT recipeId FROM Favorite")
    fun getFavId(): Flow<List<Int>>

    @Query("SELECT * FROM Recipe WHERE recipeId IN (:favs)")
    fun getFavorites(favs: List<Int>): Flow<List<Recipe>>










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









    @Query("DELETE FROM Dish WHERE dishId = :dishId")
    suspend fun deleteDish(dishId: Int)

    @Query("DELETE FROM Recipe WHERE recipeId = :recipeId")
    suspend fun deleteRecipe(recipeId: Int)

    @Query("DELETE FROM Favorite WHERE recipeId = :recipeId")
    suspend fun deleteFavorite(recipeId: Int)

}