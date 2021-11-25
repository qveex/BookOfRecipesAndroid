package com.example.bookofrecipes.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.R
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.ui.theme.Typography
import com.example.bookofrecipes.widgets.nav.Screen

@Composable
fun DishItem(dish: Dish, navController: NavController) {


    Surface(
        modifier = Modifier
            .clickable {
                navController.navigate(route = Screen.Dish.passId(dish.dishId))
            }
            .background(Color.DarkGray),
        color = Color.DarkGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(116.dp))
                .padding(14.dp)
        ) {

            Row() {

                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .weight(1f),
                    painter = painterResource(id = R.drawable.im),
                    contentDescription = "Test im"
                )

                Text(
                    text = dish.name,
                    fontSize = Typography.subtitle1.fontSize,
                    color = Color.White,
                    modifier = Modifier.weight(2f)
                )
            }

        }
    }


}


@Composable
@Preview
fun DishItemPreview() {

    DishItem(
        dish = Dish.build(
            recipes = mutableListOf<Recipe>(),
            name = "Утка по татарски"
        ) {  },
        navController = rememberNavController()
    )

}