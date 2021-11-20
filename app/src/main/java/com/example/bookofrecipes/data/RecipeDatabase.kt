package com.example.bookofrecipes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// https://github.com/stevdza-san/SearchRecyclerViewDemo/blob/master/app/src/main/java/com/example/searchrecyclerviewdemo/di/DatabaseModule.kt


/*@Database(entities = [], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun dao(): RecipeDao

}*/


/*@Database(entities = [], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun dao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RecipeDatabase::class.java,
                "recipe_database"
            ).fallbackToDestructiveMigration().build()
    }

}*/
