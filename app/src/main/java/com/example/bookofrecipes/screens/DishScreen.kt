package com.example.bookofrecipes.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.ui.theme.Typography
import com.example.bookofrecipes.widgets.DishItem
import com.example.bookofrecipes.widgets.RecipeItem

@ExperimentalFoundationApi
@Composable
fun DishScreen(navController: NavController, dishId: Int) {
    
    val dish = BookOfRecipes.findDishById(dishId)
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            //.padding(16.dp)
    ) {

        Column(

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 16.dp, 8.dp, 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    text = dish.name,
                    fontSize = Typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1
                )
            }

            if (dish.info != "") {
                RowTextCenter(text = "Dish description")
                RowTextCenter(text = dish.info)
            }
            DishDivider()
            RowTextCenter(text = "Recipes")
            DishDivider()
            RecipeList(dish = dish, navController = navController)

        }
        
    }

}

@ExperimentalFoundationApi
@Composable
fun RecipeList(dish: Dish, navController: NavController) {
    LazyColumn(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        dish.getAllCuisine().forEach { cuisine ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(6.dp),
                        //.border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(128.dp)),
                    text = "$cuisine кухня",
                    color = Color.DarkGray
                )
            }
            items(items = dish.getCuisineRecipes(cuisine = cuisine)) { recipe ->
                RecipeItem(recipe = recipe, navController = navController)
            }
        }
    }
}


@Composable
fun RowTextCenter(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text)
    }
}

@Composable
fun DishDivider() {
    Divider(
        color = Color.White,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
}