package com.example.bookofrecipes.controllers


data class Dish(

    private val recipes: List<Recipe>,
    val name: String,
    val info: String,
    val cuisine: String

) {

    val id: Int = 0

    private constructor(builder: Builder) :
            this(
                builder.recipes, builder.name,
                builder.info, builder.cuisine
            )

    companion object {
        inline fun build(
            recipes: List<Recipe>,
            name: String,
            block: Builder.() -> Unit
        ) = Builder(recipes, name).apply(block).build()
    }

    fun addRecipe(recipe: Recipe) {
        TODO()
    }

    fun removeRecipe(id: Int) {
        TODO()
    }

    fun updateRecipe(id: Int, recipe: Recipe) {
        TODO()
    }

    fun getRecipes() = recipes

    class Builder(

        val recipes: List<Recipe>,
        val name: String

    ) {

        var info: String = ""
        var cuisine: String = ""

        fun build() = Dish(this)

    }

}