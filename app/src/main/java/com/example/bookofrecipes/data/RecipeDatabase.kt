package com.example.bookofrecipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookofrecipes.models.*

// https://github.com/stevdza-san/SearchRecyclerViewDemo/blob/master/app/src/main/java/com/example/searchrecyclerviewdemo/di/DatabaseModule.kt


@Database(
    entities = [
        Dish::class,
        Recipe::class,
        CookingStep::class,
        RecipeIngredientCrossRef::class,
        IngredientEntity::class],
    version = 1,
    exportSchema = false)

abstract class RecipeDatabase: RoomDatabase() {

    abstract fun dao(): RecipeDao

}