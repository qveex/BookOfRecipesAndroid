package com.example.bookofrecipes.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Recipe

@Composable
fun DishItem(dish: Dish) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(14.dp),
        //verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = dish.name,
            fontSize = Typography.h5.fontSize,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = dish.cuisine,
            fontSize = Typography.body1.fontSize,
            fontWeight = FontWeight.Normal
        )

    }

}


@Composable
@Preview
fun DishItemPreview() {

    DishItem(
        dish = Dish.build(
            recipes = listOf<Recipe>(),
            name = "Утка по татарски"
        ){ cuisine = "Татарская" }
    )

}