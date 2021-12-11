package com.example.bookofrecipes.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.bookofrecipes.models.IngredientEntity

@Composable
fun IngDropDownMenu(
    ings: List<IngredientEntity>,
    label: String,
    onClicked: (IngredientEntity) -> Unit,
) {

    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero)}
    var expendedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expendedState) 180f else 0f)


    Column(
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() },
            label = { Text(label) },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
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
        )
        DropdownMenu(
            expanded = expendedState,
            onDismissRequest = { expendedState = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            ings.forEach {
                DropdownMenuItem(
                    onClick = { onClicked(it) }
                ) {
                    Text(text = it.name)
                }
            }
        }
    }
}


@Composable
fun IngList(
    ings: List<IngredientEntity>,
    onClicked: (IngredientEntity) -> Unit
) {

    Row(
        modifier = Modifier
            .padding(6.dp)
            .horizontalScroll(rememberScrollState())
            .background(Color.DarkGray)
    ) {
        ings.forEach {
            IngItem(ing = it) { onClicked(it) }
        }
    }

}

@Composable
fun IngItem(
    ing: IngredientEntity,
    onClicked: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .padding(1.dp)
                .clickable { onClicked() }
        ) {
            Text(
                modifier = Modifier.padding(2.dp),
                text = ing.name,
                fontSize = 14.sp,
            )
        }
    }
}


@Composable
@Preview
fun IngItemPreview() {
    IngItem(ing = IngredientEntity("Чеснок")) {}
}