package com.example.bookofrecipes.controllers

import com.example.bookofrecipes.models.Ingredient

object BookOfRecipes {

    private val dishes: List<Dish> = mutableListOf(
        Dish.build(
            recipes = listOf<Recipe>(),
            name = "Курица по чеченски"
        ){ cuisine = "Чеченская" },

        Dish.build(
            recipes = listOf<Recipe>(),
            name = "Утка по татарски"
        ){ cuisine = "Татарская" },

        Dish.build(
            recipes = listOf<Recipe>(),
            name = "Кекс без ысходность"
        ){ cuisine = "Домашняя" },

        Dish.build(
            recipes = listOf<Recipe>(),
            name = "Пицца из батона"
        ){ cuisine = "Домашняя" },
    )

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

    fun getAllCuisine() = mutableSetOf<String>().apply {
        dishes.forEach { this.add(it.cuisine) }
    }

    fun getDishes(cuisine: String) = dishes.filter { it.cuisine == cuisine }
}