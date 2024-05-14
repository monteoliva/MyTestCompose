package com.example.components

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

import com.example.components.core.theme.ComponentComposeTheme
import com.example.components.core.theme.Green300
import com.example.components.core.theme.Red300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeCardItem(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = 10.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    elevation: Dp = 8.dp,
    onSwipeLeft:  () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    onClick:      () -> Unit = {},
    showContent: @Composable (ColumnScope.() -> Unit)
) {
    val accept  = SwipeAction(
        background = Green300,
        onSwipe    = { onSwipeLeft.invoke() },
        icon       = {
            Icon(
                modifier    = Modifier.padding(all = 12.dp),
                imageVector = Icons.Filled.Check,
                tint        = Color.White,
                contentDescription = null
            )
        }
    )
    val reject = SwipeAction(
        background = Red300,
        onSwipe    = { onSwipeRight.invoke() },
        icon       = {
            Icon(
                modifier    = Modifier.padding(all = 12.dp),
                imageVector = Icons.Outlined.Close,
                tint        = Color.White,
                contentDescription = null
            )
        }
    )

    SwipeableActionsBox(
        modifier     = Modifier.padding(all = 4.dp),
        startActions = listOf(reject),
        endActions   = listOf(accept),
        backgroundUntilSwipeThreshold = Color.White
    )
    {
        Card(
            shape     = shape,
            elevation = CardDefaults.cardElevation(defaultElevation = elevation),
            modifier  = modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing         = LinearOutSlowInEasing
                    )
                ),
            onClick = { onClick.invoke() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
            ) {
                showContent()
            }
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
private fun SwipeCardItemPreview() {
    ComponentComposeTheme {
        SwipeCardItem(
            onSwipeLeft  = {},
            onSwipeRight = {}
        ) {
            Text(
                text     = "Mostra dados do Card - Item",
                color    = Color.White,
                modifier = Modifier.padding(all = 14.dp)
            )
        }
    }
}