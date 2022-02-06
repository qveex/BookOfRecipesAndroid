package com.example.bookofrecipes.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class Dish(

    val name: String,
    val info: String,
    val bitmap: Bitmap? = null

) {

    private constructor(name: String, info: String, bitmap: Bitmap?, recipes: MutableList<Recipe>) : this(
        name,
        info,
        bitmap
    ) {
        this.recipes = recipes
    }

    @PrimaryKey(autoGenerate = true)
    var dishId: Int = 0
    @Ignore
    private var recipes: MutableList<Recipe> = mutableListOf()

    private constructor(builder: Builder) :
            this(
                builder.name,
                builder.info,
                builder.bitmap,
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
        if (!recipes.contains(recipe)) recipes.add(recipe)
    }

    fun addRecipes(recipes: List<Recipe>) {
        this.recipes.addAll(recipes)
    }

    fun removeRecipe(id: Int) = recipes.removeIf{ it.dishId == id }

    fun updateRecipe(id: Int, recipe: Recipe) {
        TODO()
    }


    fun getAllCuisine() = mutableSetOf<String>().apply {
        recipes.forEach { recipe ->
            this.add(recipe.cuisine)
        }
    }

    fun getCuisineRecipes(cuisine: String) = recipes.filter { it.cuisine == cuisine }


    fun getRecipes() = recipes

    class Builder(

        val recipes: MutableList<Recipe>,
        val name: String,

    ) {

        val bitmap: Bitmap? = null
        var info: String = ""

        fun build() = Dish(this)

    }

    override fun toString(): String {
        return "Dish(name=$name, info=$info, id=$dishId)"
    }
}