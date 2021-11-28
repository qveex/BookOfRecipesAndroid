package com.example.bookofrecipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookofrecipes.models.*

@Database(
    entities = [
        Dish::class,
        Recipe::class,
        CookingStep::class,
        RecipeIngredientCrossRef::class,
        IngredientEntity::class,
        Favorite::class],
    version = 1,
    exportSchema = false
)

abstract class RecipeDatabase : RoomDatabase() {

    abstract fun dao(): RecipeDao

}