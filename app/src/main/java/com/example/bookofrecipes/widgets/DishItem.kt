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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
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

            Row(
                horizontalArrangement = Arrangement.Center
            ) {

                if (dish.bitmap != null) {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .weight(1f),
                        contentScale = ContentScale.Crop,
                        bitmap = dish.bitmap.asImageBitmap(),
                        contentDescription = "Test im"
                    )
                }
                else {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .weight(1f),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.ic_baseline_image_128),
                        contentDescription = "Test im"
                    )
                }


                Box(Modifier.weight(2f)) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = dish.name,
                            fontSize = Typography.subtitle1.fontSize,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = if (dish.info.length < 110) dish.info else dish.info.substring(0, 110) + "...",
                            fontSize = if (dish.info.length < 100) Typography.body1.fontSize else Typography.body2.fontSize,
                            color = Color.White
                        )
                    }
                }

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
        ) { info = "Сочная утка с необычным послевкусием и секретным ингридиентом kfkfkfkfk kfkfkf kfkfkfk fkfkfkf fkfkfkfk fafaf fffff fffff" },
        navController = rememberNavController()
    )

}