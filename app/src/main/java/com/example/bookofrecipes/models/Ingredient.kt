package com.example.bookofrecipes.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
abstract class Ingredient {

    @PrimaryKey(autoGenerate = true)
    val ingredientId: Int = 0
    val name: String = ""
    val number: Double = .0
    val measure: String = ""

    abstract fun getIng(): String

}