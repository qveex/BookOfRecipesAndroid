package com.example.bookofrecipes.models

interface Ingredient {

    val id: Int
    val name: String
    val type: String
    val description: String
    val measure: String

    fun getIng(): String

}