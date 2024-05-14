package com.example.components.expandable

import android.content.res.Configuration
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Close
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
fun ExpandableSwipeCardItem(
    onSwipeLeft:  () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    shownContent:  @Composable (ColumnScope.() -> Unit),
    hiddenContent: @Composable (ColumnScope.() -> Unit)
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
        backgroundUntilSwipeThreshold = Color.Transparent
    )
    {
        ExpandableCardComponent(
            shownContent  = { shownContent()  },
            hiddenContent = { hiddenContent() }
        )
    }
}

@Composable
@Preview(
    name           = "Light Mode",
    showBackground = false
)
@Preview(
    uiMode         = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name           = "Dark Mode"
)
private fun ExpandableSwipeCardItemPreview() {
    ComponentComposeTheme {
        ExpandableSwipeCardItem(
            onSwipeLeft  = {},
            onSwipeRight = {},
            shownContent = {
                Text(
                    text     = "Sempre mostra",
                    color    = Color.White,
                    modifier = Modifier.padding(all = 8.dp)
                )
            },
            hiddenContent = {
                Text(
                    text     = "Mostra somente quando expande",
                    color    = Color.White,
                    modifier = Modifier.padding(all = 8.dp)
                )
            }
        )
    }
}