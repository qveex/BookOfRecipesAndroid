package com.example.bookofrecipes.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.models.Dish

@Composable
fun DishList(dishes: List<Dish>, navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(items = dishes) { dish ->
            DishItem(dish = dish, navController = navController)
        }
    }
}