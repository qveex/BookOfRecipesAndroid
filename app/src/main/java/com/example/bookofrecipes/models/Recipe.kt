package com.example.bookofrecipes.models

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = Dish::class,
            parentColumns = ["dishId"],
            childColumns = ["recipeId"]
        )
    ]
)
data class Recipe(

    @PrimaryKey(autoGenerate = true)
    var recipeId: Int,
    var dishId: Int,

    @Embedded val description: Description?,
    val name: String,
    val cookingTime: Double,
    val cuisine: String,
    val cost: Double,
    val complexity: Byte,
    val spicy: Byte,
    //val image: Image

) {

    @Ignore private var steps: List<CookingStep> = emptyList()
    @Ignore private var ingredients: List<Ingredient> = emptyList()

    private constructor(
        recipeId: Int, dishId: Int,
        steps: List<CookingStep>,
        ingredients: List<Ingredient>,
        description: Description?,
        name: String,
        cookingTime: Double,
        cuisine: String,
        cost: Double, complexity:
        Byte, spicy: Byte
    ): this(recipeId, dishId, description, name, cookingTime, cuisine, cost, complexity, spicy) {
        this.steps = steps
        this.ingredients = ingredients
    }

    private constructor(builder: Builder) :
            this(
                0, 0, builder.steps,
                builder.ingredients, builder.description,
                builder.name, builder.cookingTime,
                builder.cuisine, builder.cost,
                builder.complexity, builder.spicy
            )

    companion object {
        inline fun build(
            name: String,
            steps: List<CookingStep>,
            ingredients: List<Ingredient>,
            block: Builder.() -> Unit
        ) = Builder(name, steps, ingredients).apply(block).build()
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

        val name: String,
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