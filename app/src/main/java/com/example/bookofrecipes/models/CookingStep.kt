package com.example.bookofrecipes.models

import android.media.Image
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            onDelete = CASCADE,
            entity = Recipe::class,
            parentColumns = ["recipeId"],
            childColumns = ["stepId"]
        )
    ]
)
data class CookingStep(

    @PrimaryKey(autoGenerate = true)
    val stepId: Int,
    var recipeId: Int,

    val title: String,
    val info: String,
    val time: Int,
    //val image: Image

)
