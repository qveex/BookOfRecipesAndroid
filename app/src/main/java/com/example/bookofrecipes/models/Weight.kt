package com.example.bookofrecipes.models

data class Weight(
    override val id: Int,
    override val name: String,
    override val number: Double,
    override val measure: String
) : Ingredient {


    override fun getIng(): String {
        TODO("Not yet implemented")
    }

    fun kilogram() = .0
    fun gram() = .0
    fun glasses() = 0
}
