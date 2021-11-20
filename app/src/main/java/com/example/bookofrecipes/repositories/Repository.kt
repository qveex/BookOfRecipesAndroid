package com.example.bookofrecipes.repositories

import com.example.bookofrecipes.data.RecipeDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val recipeDao: RecipeDao
) {



}