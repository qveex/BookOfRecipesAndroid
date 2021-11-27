package com.example.bookofrecipes.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

data class Ingredient (

    var name: String,
    var number: Double,
    var measure: String

) {

    fun getIng(): String = ""

    override fun toString(): String {
        return "Ingredient(name=$name, number=$number, measure=$measure)"
    }
}


@Entity(indices = [Index(value = ["name"], unique = true)])
data class IngredientEntity(

    val name: String

) {
    @PrimaryKey(autoGenerate = true)
    var ingredientId = 0
}
