package com.example.bookofrecipes.controllers

import android.media.Image
import com.example.bookofrecipes.models.CookingStep
import com.example.bookofrecipes.models.Description
import com.example.bookofrecipes.models.Ingredient

data class Recipe(

    private val steps: List<CookingStep>,
    private val ingredients: List<Ingredient>,

    val description: Description?,
    val cookingTime: Double,
    val cost: Double,
    val complexity: Byte,
    val spicy: Byte,
    //val image: Image

) {

    val id: Int = 0

    private constructor(builder: Builder) :
            this(
                builder.steps, builder.ingredients,
                builder.description, builder.cookingTime,
                builder.cost, builder.complexity, builder.spicy
            )

    companion object {
        inline fun build(
            steps: List<CookingStep>,
            ingredients: List<Ingredient>,
            block: Builder.() -> Unit
        ) = Builder(steps, ingredients).apply(block).build()
    }

    fun addStep(step: CookingStep) {
        TODO()
    }

    fun removeStep(id: Int) {
        TODO()
    }

    fun updateStep(id: Int, updStep: CookingStep) {
        TODO()
    }

    fun getSteps() = steps
    fun getIngredients() = ingredients


    class Builder(

        val steps: List<CookingStep>,
        val ingredients: List<Ingredient>

    ) {

        var description: Description? = null
        var cookingTime: Double = .0
        var cost: Double = .0
        var complexity: Byte = 0
        var spicy: Byte = 0
        //var image: Image? = null

        fun build() = Recipe(this)

    }

}