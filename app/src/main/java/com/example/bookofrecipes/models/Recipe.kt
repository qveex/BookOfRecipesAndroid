package com.example.bookofrecipes.models

import androidx.room.*

@Entity
data class Recipe(

    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,
    var dishId: Int,

    @Ignore private val steps: List<CookingStep>,
    @Ignore private val ingredients: List<Ingredient>,

    @Embedded val description: Description?,
    val cookingTime: Double,
    val cuisine: String,
    val cost: Double,
    val complexity: Byte,
    val spicy: Byte,
    //val image: Image

) {

    private constructor(builder: Builder) :
            this(
                0, 0,
                builder.steps, builder.ingredients,
                builder.description, builder.cookingTime,
                builder.cuisine, builder.cost,
                builder.complexity, builder.spicy
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
        var cuisine: String = "Другое"
        var cost: Double = .0
        var complexity: Byte = 0
        var spicy: Byte = 0
        //var image: Image? = null

        fun build() = Recipe(this)

    }

}