package com.example.bookofrecipes.models

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CookingStep(

    @PrimaryKey(autoGenerate = true)
    val stepId: Int,
    var recipeId: Int,

    val title: String,
    val info: String,
    val time: String,
    //val image: Image

)
