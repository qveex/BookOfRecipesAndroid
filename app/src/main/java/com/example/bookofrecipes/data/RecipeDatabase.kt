package com.example.bookofrecipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookofrecipes.models.*
import com.example.bookofrecipes.utills.Converters

@Database(
    entities = [
        Dish::class,
        Recipe::class,
        CookingStep::class,
        RecipeIngredientCrossRef::class,
        IngredientEntity::class,
        Favorite::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun dao(): RecipeDao

}