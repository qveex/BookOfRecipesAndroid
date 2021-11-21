package com.example.bookofrecipes.di

import android.content.Context
import androidx.room.Room
import com.example.bookofrecipes.data.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        "recipe_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: RecipeDatabase) = database.dao()

}