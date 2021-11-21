package com.example.bookofrecipes.controllers

import androidx.activity.viewModels
import com.example.bookofrecipes.models.CookingStep
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Ingredient
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.viewmodel.RecipeViewModel

object BookOfRecipes {

    private val dishes: List<Dish> = mutableListOf(
        Dish.build(
            recipes = mutableListOf<Recipe>(Recipe.build(
                mutableListOf<CookingStep>(),
                mutableListOf<Ingredient>()
            ) { cuisine = "Чеченская" }),
            name = "Курица по чеченски"
        ){  },

        Dish.build(
            recipes = mutableListOf<Recipe>(Recipe.build(
                mutableListOf<CookingStep>(),
                mutableListOf<Ingredient>()
            ) { cuisine = "Татарская" }),
            name = "Утка по татарски"
        ){  },

        Dish.build(
            recipes = mutableListOf<Recipe>(Recipe.build(
                mutableListOf<CookingStep>(),
                mutableListOf<Ingredient>()
            ) {  }),
            name = "Кекс Безысходность"
        ){  },

        Dish.build(
            recipes = mutableListOf<Recipe>(Recipe.build(
                mutableListOf<CookingStep>(),
                mutableListOf<Ingredient>()
            ) {  }),
            name = "Пицца из батона"
        ){  },
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

    /*fun getAllCuisine() = mutableSetOf<String>().apply {
        dishes.forEach { this.add(it.cuisine) }
    }*/

    //fun getDishes(cuisine: String) = dishes.filter { it.cuisine == cuisine }
}