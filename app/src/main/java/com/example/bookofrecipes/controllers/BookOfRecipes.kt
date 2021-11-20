package com.example.bookofrecipes.controllers

import com.example.bookofrecipes.models.Ingredient

object BookOfRecipes {

    private val dishes: List<Dish> = emptyList()

    fun addDish(dish: Dish) {
        TODO()
    }

    fun removeDish(id: Int) {
        TODO()
    }

    fun updateDish(id: Int, dish: Dish) {
        TODO()
    }

    fun findDishes(name: String): List<Dish> {
        TODO()
    }

    fun findDishes(ingredients: List<Ingredient>): List<Dish> {
        TODO()
    }

    fun getAllDishes() = dishes
}