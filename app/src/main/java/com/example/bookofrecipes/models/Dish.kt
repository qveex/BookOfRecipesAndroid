package com.example.bookofrecipes.models

import androidx.room.*


@Entity
data class Dish(

    val name: String,
    val info: String

) {

    private constructor(name: String, info: String, recipes: MutableList<Recipe>): this(name, info){
        this.recipes = recipes
    }

    @PrimaryKey(autoGenerate = true)
    var dishId: Int = 0
    @Ignore private var recipes: MutableList<Recipe> = mutableListOf()

    private constructor(builder: Builder) :
            this(
                builder.name,
                builder.info,
                builder.recipes,
            )

    companion object {
        inline fun build(
            recipes: MutableList<Recipe>,
            name: String,
            block: Builder.() -> Unit
        ) = Builder(recipes, name).apply(block).build()
    }

    fun addRecipe(recipe: Recipe) {
        TODO()
    }

    fun addRecipes(recipes: List<Recipe>) {
        this.recipes.addAll(recipes)
    }

    fun removeRecipe(id: Int) {
        TODO()
    }

    fun updateRecipe(id: Int, recipe: Recipe) {
        TODO()
    }

    fun getRecipes() = recipes

    class Builder(

        val recipes: MutableList<Recipe>,
        val name: String

    ) {

        var info: String = ""

        fun build() = Dish(this)

    }

}