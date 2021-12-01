package com.example.bookofrecipes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddDishScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var dishName by remember { mutableStateOf("") }
            var dishInfo by remember { mutableStateOf("") }

            TitleRowTextCenter(text = "Create your own dish!")

            OutlinedTextField(
                modifier = Modifier
                    .padding(42.dp, 16.dp, 42.dp, 16.dp)
                    .fillMaxWidth(),
                value = dishName,
                onValueChange = { dishName = it },
                label = { Text("Dish name*") },
                singleLine = true

            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = dishInfo,
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                    }
                },
                onValueChange = { dishInfo = it },
                label = { Text("Info") },
                maxLines = 7

            )


        }
    }
}


@Composable
@Preview
fun PreviewAddDish() {
    AddDishScreen(rememberNavController())
}