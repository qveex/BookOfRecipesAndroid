package com.example.bookofrecipes.screens

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookofrecipes.R
import com.example.bookofrecipes.models.Dish
import com.example.bookofrecipes.viewmodel.RecipeViewModel
import com.example.bookofrecipes.widgets.nav.Screen

@Composable
fun AddDishScreen(navController: NavController, viewModel: RecipeViewModel) {

    var dishName by remember { mutableStateOf("") }
    var dishInfo by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleRowTextCenter(text = "Create your own dish!")

            if (imageUri != null) {
                bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "image",
                        modifier = Modifier
                            .size(300.dp, 200.dp)
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentScale = ContentScale.Fit
                    )
                }
            } else {
                Image(
                    modifier = Modifier.clickable {
                        launcher.launch("image/*")
                    },
                    painter = painterResource(id = R.drawable.ic_baseline_image_128),
                    contentDescription = "Get image picture"
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .padding(42.dp, 16.dp, 42.dp, 16.dp)
                    .fillMaxWidth(),
                value = dishName,
                onValueChange = { dishName = it },
                label = { Text("Dish name") },
                singleLine = true

            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                value = dishInfo,
                leadingIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                    }
                },
                onValueChange = { dishInfo = it },
                label = { Text("Info") },
                maxLines = 7

            )

            OutlinedAddButton(
                text = "create!",
                onClicked = {
                    viewModel.insertDish(Dish(dishName, dishInfo, bitmap))
                    navController.navigate(Screen.Dishes.route)
                }
            )


        }
    }
}


@Composable
@Preview
fun PreviewAddDish() {
    //AddDishScreen(rememberNavController())
}