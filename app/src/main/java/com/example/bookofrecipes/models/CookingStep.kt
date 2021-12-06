package com.example.bookofrecipes.models

import androidx.room.Entity
import androidx.room.ForeignKey
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
data class CookingStep(

    val title: String,
    val info: String,
    val time: Int,
    var number: Int
    //val image: Image

) {
    @PrimaryKey(autoGenerate = true)
    var stepId: Int = 0
    var recipeId: Int = 0

    override fun toString(): String {
        return "Step(id=$stepId, recipeId=$recipeId, title=$title, info=$info, time=$time)"
    }
}
