package com.example.bookofrecipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookofrecipes.models.*
import com.example.bookofrecipes.utills.Converters

// Указываем, что класс является БД
@Database(
    // сущности (таблицы) базы данных
    entities = [
        Dish::class,
        Recipe::class,
        CookingStep::class,
        RecipeIngredientCrossRef::class,
        IngredientEntity::class,
        Favorite::class],
    version = 1
)
// Указываем вспомогательных класс для преобразования типов
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {

    // абстрактный метод для объекта доступа к БД
    abstract fun dao(): RecipeDao

}
