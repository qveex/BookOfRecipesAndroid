package com.example.bookofrecipes.models

import com.example.bookofrecipes.controllers.Recipe

data class Piece(
    val capacity: Double,
    override val id: Int,
    override val name: String,
    override val type: String,
    override val description: String,
    override val measure: String
) : Ingredient {


    override fun getIng(): String {
        TODO("Not yet implemented")
        /*val recipe = Recipe.build(
            listOf<CookingStep>(),
            listOf<Ingredient>()
        ) {
            cost = .0
        }*/
    }
}
