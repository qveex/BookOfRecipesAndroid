package com.example.bookofrecipes.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class DishWithRecipes(

    @Embedded val dish: Dish,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "dishId"
    )
    val recipes: List<Recipe>

)


data class RecipeWithSteps(

    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeId"
    )
    val steps: List<CookingStep>

)


@Entity(primaryKeys = ["recipeId", "ingredientId"])
data class RecipeIngredientCrossRef(

    val recipeId: Int,
    val ingredientId: Int

)


data class RecipeWithIngredients(

    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    )
    val ingredients: List<Ingredient>
)


data class IngredientWithRecipes(

    @Embedded val ingredient: Ingredient,
    @Relation(
        parentColumn = "ingredientId",
        entityColumn = "recipeId",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    )
    val recipes: List<Recipe>

)