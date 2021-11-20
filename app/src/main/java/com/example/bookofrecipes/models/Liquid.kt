package com.example.bookofrecipes.models

data class Liquid(
    val number: Int,
    override val id: Int,
    override val name: String,
    override val type: String,
    override val description: String,
    override val measure: String
) : Ingredient {


    override fun getIng(): String {
        TODO("Not yet implemented")
    }

    fun milliliter() = .0
    fun liter() = .0
    fun glasses() = 0
    fun spoons() = 0
}
