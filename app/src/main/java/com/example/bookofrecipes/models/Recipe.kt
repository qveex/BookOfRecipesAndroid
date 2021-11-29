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

    @Embedded val description: Description?,
    val name: String,
    val cookingTime: Int,
    val cuisine: String,
    val cost: Int,
    val complexity: Byte,
    val spicy: Byte,
    //val image: Image

) {

    @PrimaryKey(autoGenerate = true)
    var recipeId: Int = 0
    var dishId: Int = 0

    @Ignore private var steps: List<CookingStep> = emptyList()
    @Ignore private var ingredients: List<Ingredient> = emptyList()

    private constructor(
        steps: List<CookingStep>,
        ingredients: List<Ingredient>,
        description: Description?,
        name: String,
        cookingTime: Int,
        cuisine: String,
        cost: Int,
        complexity: Byte,
        spicy: Byte
    ): this(description, name, cookingTime, cuisine, cost, complexity, spicy) {
        this.steps = steps
        this.ingredients = ingredients
    }

    private constructor(builder: Builder) :
            this(
                builder.steps,
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
        var cookingTime: Int = 0
        var cuisine: String = "Другая"
        var cost: Int = 0
        var complexity: Byte = 0
        var spicy: Byte = 0
        //var image: Image? = null

        fun build() = Recipe(this)

    }

    override fun toString(): String {
        return "Recipe(id=$recipeId, dishId=$dishId, description=$description, name=$name, time=$cookingTime, cuisine=$cuisine, cost=$cost, complexity=$complexity, spicy=$spicy, ings=$ingredients, steps=$steps)"
    }
}