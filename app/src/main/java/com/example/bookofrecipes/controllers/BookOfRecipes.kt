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
                    mutableListOf<CookingStep>(
                        CookingStep(0, 4, "Помыть", "Поместите утку в ванну и тщательно промойте ее под душем", 10),
                        CookingStep(1, 4, "Мариновать", "Обработайте утку специями и соусами по вкусу и оставьте ее в таком состоянии на некоторое время для более яркого вкуса", 60),
                        CookingStep(2, 4, "Запечь", "Предварительно разогрейте духовку до 451 градуса по Френгейту и поместите туда утку на протвени, ожидайте", 60),
                        CookingStep(3, 4, "Разрезать", "После полной готовности, достаньте утку из духовки и разрежте ее на куски удобного размера", 15),
                        CookingStep(4, 4, "Приятного аппетита!", "Разложите по тарелкам с овощами по желанию", 5),
                    ),
                    mutableListOf<Ingredient>(
                        Ingredient("Утка", 1.0, "шт"),
                        Ingredient("Масло", 50.0, "г"),
                        Ingredient("Соус", 50.0, "г")
                    )
                ) {
                    cuisine = "Татарская"
                    complexity = 4
                    cost = 750
                    cookingTime = 150
                    spicy = 1
                }.apply { recipeId = 4 }),
            name = "Запеченая утка"
        ) { info = "Утка вкусная шикарная румяная" }).apply { dishId = 1 },

        (Dish.build(
            recipes = mutableListOf<Recipe>(
                Recipe.build(
                    "Кекс Безысходность",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { }.apply { recipeId = 5 }),
            name = "Кекс"
        ) { }).apply { dishId = 2 },

        (Dish.build(
            recipes = mutableListOf<Recipe>(
                Recipe.build(
                    "Пицца из батона",
                    mutableListOf<CookingStep>(),
                    mutableListOf<Ingredient>()
                ) { }.apply { recipeId = 6 }),
            name = "Пицца"
        ) { }).apply { dishId = 3 },
    )

    private val favorites: MutableSet<Int> = mutableSetOf(
        1, 2
    )

    fun getFavorites() = mutableListOf<Recipe>().apply {
        dishes.forEach { dish ->
            this.addAll(dish.getRecipes().filter { favorites.contains(it.recipeId) })
        }
    }
    fun isFav(recipeId: Int) = favorites.contains(recipeId)
    fun addFavorite(recipeId: Int) = favorites.add(recipeId)
    fun removeFavorite(recipeId: Int) = favorites.removeIf { it == recipeId }

    fun addDish(dish: Dish) {
        if (!dishes.contains(dish)) dishes.add(dish)
    }

    fun removeDish(dishId: Int): Boolean {
        dishes.forEach { dish ->
            if (dish.dishId == dishId) {
                dish.getRecipes().forEach { recipe ->
                    removeFavorite(recipeId = recipe.recipeId)
                }
                dishes.remove(dish)
                return true
            }
        }
        return false
    }//= dishes.removeIf { it.dishId == id }

    fun updateDish(id: Int, dish: Dish) {
        TODO()
    }

    fun findDishes(name: String = "") =
        if (name != "")
            dishes.filter { it.name.contains(name) }
        else getAllDishes()


    fun findDishById(id: Int) = dishes.find { it.dishId == id }

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
    /* @this is about all recipe's functions now */
    fun getRecipe(recipeId: Int): Recipe? {

        dishes.forEach { dish ->
            val f = dish.getRecipes().find { it.recipeId == recipeId }
            if (f != null) return f
        }
        return null
    }

    fun removeRecipe(recipeId: Int): Boolean {
        removeFavorite(recipeId = recipeId)
        dishes.forEach { dish ->
            if (dish.getRecipes().removeIf{ it.recipeId == recipeId }) return true
        }
        return false
    }
}