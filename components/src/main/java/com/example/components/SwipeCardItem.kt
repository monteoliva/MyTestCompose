package com.example.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

import com.example.components.core.theme.ComponentComposeTheme
import com.example.components.core.theme.Green300
import com.example.components.core.theme.Red300

@Composable
fun SwipeCardItem(
    onSwipeLeft:  () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    val accept  = SwipeAction(
        background = Green300,
        onSwipe    = { onSwipeLeft.invoke() },
        icon       = {
            Icon(
                modifier           = Modifier.padding(12.dp),
                imageVector        = Icons.Filled.Check,
                contentDescription = null,
                tint               = Color.White
            )
        }
    )
    val reject = SwipeAction(
        background = Red300,
        onSwipe    = { onSwipeRight.invoke() },
        icon       = {
            Icon(
                modifier           = Modifier.padding(12.dp),
                imageVector        = Icons.Outlined.Close,
                contentDescription = null,
                tint               = Color.White
            )
        }
    )

    SwipeableActionsBox(
        modifier                      = Modifier.padding(4.dp),
        startActions                  = listOf(reject),
        endActions                    = listOf(accept),
        backgroundUntilSwipeThreshold = Color.White
    )
    {
        Column(content = content)
    }
}

@Preview(
    name           = "Light Mode",
    showBackground = true
)
@Preview(
    uiMode         = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name           = "Dark Mode"
)
@Composable
private fun SwipeCardItemPreview() {
    ComponentComposeTheme {
        SwipeCardItem(
            onSwipeLeft  = {},
            onSwipeRight = {}
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    text     = "Mostra dados do Card - Item",
                    color    = Color.White,
                    modifier = Modifier.padding(all = 8.dp)
                )
            }

        }
    }
}