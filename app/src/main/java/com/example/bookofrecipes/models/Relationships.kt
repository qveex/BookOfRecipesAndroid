package com.example.bookofrecipes.models

import androidx.room.*

@Entity(
    primaryKeys = ["recipeId", "ingredientId"],
    foreignKeys =
    [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = Recipe::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"]
        ),
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = IngredientEntity::class,
            parentColumns = ["ingredientId"],
            childColumns = ["ingredientId"]
        )
    ]
)
data class RecipeIngredientCrossRef(

    val recipeId: Int,
    val ingredientId: Int,

    val number: Int,
    val measure: String

)
