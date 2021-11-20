package com.example.bookofrecipes.models

data class Description(

    private val calorie: Double,
    private val proteins: Double,
    private val fats: Double,
    private val carbohydrates: Double,

) {
    fun calorie() = calorie
    fun proteins() = calorie
    fun fats() = calorie
    fun carbohydrates() = calorie
}
