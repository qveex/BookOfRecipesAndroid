package com.example.bookofrecipes.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Ingredient (

    @PrimaryKey(autoGenerate = true)
    var ingredientId: Int,
    var name: String,
    var number: Double,
    var measure: String

) {
    fun getIng(): String = ""
}



/*
@Entity(indices = [Index(value = ["name"], unique = true)])
abstract class Ingredient {

    @PrimaryKey(autoGenerate = true)
    var ingredientId: Int = 0
    var name: String = ""
    var number: Double = .0
    var measure: String = ""

    abstract fun getIng(): String

}
 */