package com.example.bookofrecipes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.bookofrecipes.repositories.Repository

class RecipeViewModel @ViewModelInject constructor(
    private val repository: Repository
): ViewModel() {



}