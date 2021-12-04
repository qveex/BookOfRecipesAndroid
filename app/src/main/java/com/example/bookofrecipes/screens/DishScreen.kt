package com.example.bookofrecipes.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookofrecipes.R
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.models.Recipe
import com.example.bookofrecipes.ui.theme.Typography
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.DishAppBar
import com.example.bookofrecipes.widgets.RecipeItem
import com.example.bookofrecipes.widgets.nav.Screen

@ExperimentalFoundationApi
@Composable
fun DishScreen(navController: NavController, viewModel: RecipeViewModel, dishId: Int) {

    //val dish = BookOfRecipes.findDishById(dishId)
    val dish by viewModel.dish(dishId).collectAsState(initial = null)
    val recipes by viewModel.recipes(dishId).collectAsState(initial = emptyList())

    if (dish != null) {
        Scaffold(
            Modifier.padding(0.dp,0.dp,0.dp,50.dp),
            topBar = {
                DishAppBar(
                    onDeleteClicked = {
                        navController.navigate(Screen.Dishes.route)
                        BookOfRecipes.removeDish(dishId)
                    },
                    onBackClicked = { navController.popBackStack() },
                    onAddClicked = { navController.navigate(route = Screen.AddRecipe.passId(dishId)) },
                    title = dish!!.name
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        modifier = Modifier
                            .size(300.dp, 200.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .padding(12.dp),
                        contentScale = ContentScale.Fit,
                        painter = painterResource(id = R.drawable.im),
                        contentDescription = "Test im"
                    )
                    RowTextCenter(text = dish!!.info)
                    DishDivider()
                    RowTextCenter(text = "Recipes")
                    DishDivider()
                    RecipeList(recipes, navController = navController, viewModel)
                }
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Oops, it's NullPointerException")
        }
    }

}

@ExperimentalFoundationApi
@Composable
fun RecipeList(recipes: List<Recipe>, navController: NavController, viewModel: RecipeViewModel) {
    //val items by viewModel.recipes(dish.dishId).collectAsState(initial = emptyList())
    LazyColumn(
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        /*dish.getAllCuisine().forEach { cuisine ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = CircleShape)
                        .padding(6.dp),
                    textAlign = TextAlign.Center,
                    text = "$cuisine кухня",
                    color = Color.DarkGray
                )
            }
            /*items(items = dish.getCuisineRecipes(cuisine = cuisine)) { recipe ->
                RecipeItem(recipe = recipe, navController = navController)
            }*/
            items(items = items) { recipe ->
                RecipeItem(recipe = recipe, navController = navController)
            }
        }*/
        items(items = recipes) { recipe ->
            RecipeItem(recipe = recipe, navController = navController)
        }
    }
}


@Composable
fun TitleRowTextCenter(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 16.dp, 8.dp, 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = text,
            fontSize = Typography.h5.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1
        )
    }
}


@Composable
fun RowTextCenter(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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


@ExperimentalFoundationApi
@Composable
@Preview
fun DishScreenPreview() {
    //DishScreen(navController = rememberNavController(), dishId = 0)
}