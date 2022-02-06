package com.example.bookofrecipes.data

import androidx.room.*
import com.example.bookofrecipes.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    // поиск ингредиентов по названию
    @Query("SELECT * FROM IngredientEntity WHERE name LIKE '%' || :text || '%'")
    fun getIngredients(text: String?): Flow<List<IngredientEntity>>

    // поиск идентификатора ингредиента по названию
    @Query("SELECT ingredientId FROM IngredientEntity WHERE name = :name")
    fun getIngId(name: String): Flow<Int>

    // получение ингредиентов конкретного рецепта
    @Query(
        "SELECT * FROM IngredientEntity " +
                "INNER JOIN RecipeIngredientCrossRef ON IngredientEntity.ingredientId = RecipeIngredientCrossRef.ingredientId " +
                "WHERE RecipeIngredientCrossRef.recipeId = :recipeId"
    )
    fun getRecipeIngs(recipeId: Int): Flow<List<IngredientEntity>>

    // получение ингредиентов, которых нет в конкретном блюде
    @Query(
        "SELECT * FROM IngredientEntity " +
                "WHERE ingredientId NOT IN " +
                "(SELECT ingredientId FROM RecipeIngredientCrossRef WHERE recipeId = :recipeId)"
    )
    fun getNotRecipeIngs(recipeId: Int): Flow<List<IngredientEntity>>

    // поиск блюд по названию
    @Query("SELECT * FROM Dish WHERE name = :name")
    fun getDishesByName(name: String): Flow<List<Dish>>

    @Query("SELECT * FROM Dish WHERE name LIKE '%' || :text || '%'")
    fun getDishes(text: String): Flow<List<Dish>>

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

    @Query("SELECT * FROM CookingStep WHERE recipeId = :recipeId ORDER BY number")
    fun getSteps(recipeId: Int): Flow<List<CookingStep>>

    @Query("SELECT COALESCE(SUM(time), 0) AS recipeTime FROM CookingStep WHERE recipeId = :recipeId")
    fun getRecipeTime(recipeId: Int): Flow<Int>

    @Query("SELECT COALESCE(COUNT(stepId), 0) AS count FROM CookingStep WHERE recipeId = :recipeId")
    fun getStepsCount(recipeId: Int): Int

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

    @Query("DELETE FROM CookingStep WHERE stepId = :stepId")
    suspend fun deleteStep(stepId: Int)

    @Delete
    suspend fun deleteRecipeIngredient(ref: RecipeIngredientCrossRef)

}