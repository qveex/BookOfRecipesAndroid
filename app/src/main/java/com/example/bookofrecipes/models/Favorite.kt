package com.example.bookofrecipes.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = Recipe::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"]
        )
    ]
)
data class Favorite(

    @PrimaryKey
    var recipeId: Int = 0

)