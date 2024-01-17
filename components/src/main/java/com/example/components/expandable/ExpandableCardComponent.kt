package com.example.components.expandable

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ContentAlpha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCardComponent(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = 10.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    shownContent:  @Composable (ColumnScope.() -> Unit),
    hiddenContent: @Composable (ColumnScope.() -> Unit)
) {
    val expandedState = rememberSaveable { mutableStateOf(false) }
    val rotationState = animateFloatAsState(
        targetValue = if (expandedState.value) 180f else 0f,
        label       = ""
    )

    Card(
        shape    = shape,
        modifier = modifier
            .background(color = Color.Transparent)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing         = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState.value = expandedState.value.not()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(7f)) {
                    Column(content = shownContent)
                }
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(weight = 1f)
                        .rotate(degrees = rotationState.value),
                    onClick = { expandedState.value = expandedState.value.not() }) {
                    Icon(
                        imageVector        = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Drop-Down Arrow",
                        tint               = MaterialTheme.colorScheme.surfaceTint)
                }
            }
            if (expandedState.value) { hiddenContent() }
        }
    }
}

@Preview(
    name           = "Light Mode",
    showBackground = false
)
@Preview(
    uiMode         = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name           = "Dark Mode"
)
@Composable
private fun ExpandableCardComponentPreview() {
    ExpandableCardComponent(
        shape           = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colorScheme.primary,
        shownContent    = {
            Text(
                text     = "Sempre mostra",
                color    = Color.White,
                modifier = Modifier.padding(all = 8.dp)
            )
        }
    ) {
        Text(
            text     = "Mostra somente quando expande",
            color    = Color.White,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}