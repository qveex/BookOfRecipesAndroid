package com.example.bookofrecipes.controllers

import com.example.bookofrecipes.models.CookingStep
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Ingredient
import com.example.bookofrecipes.models.Recipe

object BookOfRecipes {

    private val dishes: MutableList<Dish> = mutableListOf(
        (Dish.build(
            recipes = mutableListOf<Recipe>(

                Recipe.build(
                    "Курица по Чеченски",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Чеченская" }.apply { recipeId = 0 },

                Recipe.build(
                    "Курица по Таджикски",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Таджикская" }.apply { recipeId = 1 },

                Recipe.build(
                    "Курица по Американски",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Американская" }.apply { recipeId = 2 },

                Recipe.build(
                    "Курица по Африкански",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Африканская" }.apply { recipeId = 3 }
            ),

            name = "Запеченая курица"

        ) {
            info = "Вкусная запеченая курица с мягкой золотистой корочкой"
        }).apply { dishId = 0 },

        (Dish.build(
            recipes = mutableListOf<Recipe>(
                Recipe.build(
                    "Утка по Татарски",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Татарская" }),
            name = "Жаренная утка"
        ) { }).apply { dishId = 1 },

        (Dish.build(
            recipes = mutableListOf<Recipe>(
                Recipe.build(
                    "Кекс Безысходность",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { }),
            name = "Кекс"
        ) { }).apply { dishId = 2 },

        (Dish.build(
            recipes = mutableListOf<Recipe>(
                Recipe.build(
                    "Пицца из батона",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { }),
            name = "Пицца"
        ) { }).apply { dishId = 3 },
    )

    private val favorites: MutableList<Int> = mutableListOf(
        1, 2
    )

    fun getFavorites() = mutableListOf<Recipe>().apply {

        dishes.forEach { dish ->
            this.addAll(dish.getRecipes().filter { favorites.contains(it.recipeId) })
        }

    }

    fun addFavorite(recipeId: Int) = favorites.add(recipeId)
    fun removeFavorite(recipeId: Int) = favorites.removeIf { it == recipeId }

    fun addDish(dish: Dish) {
        if (!dishes.contains(dish)) dishes.add(dish)
    }

    fun removeDish(id: Int) = dishes.removeIf { it.dishId == id }

    fun updateDish(id: Int, dish: Dish) {
        TODO()
    }

    fun findDishes(name: String = "") =
        if (name != "")
            dishes.filter { it.name.contains(name) }
        else getAllDishes()


    fun findDishById(id: Int) = dishes.find { it.dishId == id }!!

    fun findDishes(ingredients: List<Ingredient>): List<Dish> {
        TODO()
    }

    fun getAllDishes() = dishes

    fun getAllCuisine() = mutableSetOf<String>().apply {
        dishes.forEach { dish ->
            dish.getRecipes().forEach { recipe ->
                this.add(recipe.cuisine)
            }
        }
    }

    /* @it should be deleted after connecting db!!! */
    /* @don't use it please!!! */
    fun getRecipe(recipeId: Int): Recipe {

        val recipe = Recipe.build(
            "Кажется, тут был null",
            mutableListOf<CookingStep>(),
            mutableListOf<Ingredient>()
        ) { cuisine = "Null'овская" }

        dishes.forEach { dish ->
            val f = dish.getRecipes().find { it.recipeId == recipeId }
            if (f != null) return f
        }
        return recipe
    }

    //fun getDishes(cuisine: String) = dishes.filter { it.cuisine == cuisine }
}