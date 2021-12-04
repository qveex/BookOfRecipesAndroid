package com.example.bookofrecipes.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.bookofrecipes.controllers.BookOfRecipes
import com.example.bookofrecipes.models.CookingStep
import com.example.bookofrecipes.ui.theme.Shapes
import com.example.bookofrecipes.viewmodel.RecipeViewModel

@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    shape: CornerBasedShape = Shapes.medium,
    padding: Dp = 12.dp
) {

    var expendedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expendedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expendedState = !expendedState
        }
    ) {
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = title,
                    fontWeight = titleFontWeight,
                    fontSize = titleFontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = { expendedState = !expendedState },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expendedState) {
                Text(
                    text = description,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun ExpandableTextFieldCard(
    recipeId: Int,
    onAddClicked: (CookingStep, Int) -> Unit,
    shape: CornerBasedShape = Shapes.medium,
    padding: Dp = 12.dp
) {

    var expendedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expendedState) 180f else 0f)

    var stepTitle by remember { mutableStateOf("") }
    var stepInfo by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expendedState = !expendedState
        }
    ) {
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(6f),
                    singleLine = true,
                    value = stepTitle,
                    onValueChange = { stepTitle = it },
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = { expendedState = !expendedState },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expendedState) {
                TextField(
                    modifier = Modifier
                        .padding(0.dp, 12.dp, 0.dp, 0.dp)
                        .fillMaxSize(),
                    maxLines = 5,
                    value = stepInfo,
                    onValueChange = { stepInfo = it },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onAddClicked(CookingStep(stepTitle, stepInfo, 0), recipeId)
                                stepTitle = ""
                                stepInfo = ""
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
                        }
                    }
                )

            }
        }
    }

}


@ExperimentalMaterialApi
@Composable
@Preview
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "Title",
        description = ""
    )
}
