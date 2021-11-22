package com.example.bookofrecipes.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.ui.theme.Typography

@Composable
fun DishItem(dish: Dish) {

    Column(
        modifier = Modifier
            //.background(Color.LightGray)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(116.dp))
            .padding(14.dp),
        //verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = dish.name,
            fontSize = Typography.subtitle1.fontSize,
            color = Color.White,
            //fontFamily = FontFamily.SansSerif,
            //fontWeight = FontWeight.Bold
        )

    }

}


@Composable
@Preview
fun DishItemPreview() {

    DishItem(
        dish = Dish.build(
            recipes = mutableListOf<Recipe>(),
            name = "Утка по татарски"
        ){  }
    )

}