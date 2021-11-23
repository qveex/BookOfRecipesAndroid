package com.example.bookofrecipes.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.ui.theme.Typography
import com.example.bookofrecipes.widgets.nav.Screen

@Composable
fun RecipeItem(recipe: Recipe, navController: NavController) {


    Surface(
        modifier = Modifier
            .clickable {
                navController.navigate(route = Screen.Recipe.passId(recipe.recipeId))
            }
            .background(Color.DarkGray),
        color = Color.DarkGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(116.dp))
                .padding(14.dp)
        ) {

            Text(
                text = recipe.name,
                fontSize = Typography.subtitle1.fontSize,
                color = Color.White,
            )

        }
    }


}


@Composable
@Preview
fun RecipeItemPreview() {


}

