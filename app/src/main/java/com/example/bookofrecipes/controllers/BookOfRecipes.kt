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
                ) { cuisine = "Чеченская" },

                Recipe.build(
                    "Курица по Таджикски",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Таджикская" },

                Recipe.build(
                    "Курица по Американски",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Американская" },

                Recipe.build(
                    "Курица по Африкански",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { cuisine = "Африканская" }
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




    fun addDish(dish: Dish) {
        if (!dishes.contains(dish)) dishes.add(dish)
    }

    fun removeDish(id: Int) = dishes.removeIf{ it.dishId == id }

    fun updateDish(id: Int, dish: Dish) {
        TODO()
    }

    fun findDishes(name: String) = dishes.filter { it.name == name }


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


    //fun getDishes(cuisine: String) = dishes.filter { it.cuisine == cuisine }
}