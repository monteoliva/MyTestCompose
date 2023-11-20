package com.example.mytestcompose.ui.components.expandable

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

import com.example.mytestcompose.ui.theme.Green300
import com.example.mytestcompose.ui.theme.MyTestComposeTheme
import com.example.mytestcompose.ui.theme.Red300

@Composable
fun ExpandableSwipeCardItem(
    onSwipeLeft:  () -> Unit,
    onSwipeRight: () -> Unit,
    shownContent:  @Composable (ColumnScope.() -> Unit),
    hiddenContent: @Composable (ColumnScope.() -> Unit)
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
        ExpandableCardComponent(
            shownContent  = { shownContent()  },
            hiddenContent = { hiddenContent() }
        )
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
fun ExpandableSwipeCardItemPreview() {
    MyTestComposeTheme {
        ExpandableSwipeCardItem(
            onSwipeLeft  = {},
            onSwipeRight = {},
            shownContent = {
                Text(
                    text     = "Title",
                    color    = Color.White,
                    modifier = Modifier.padding(all = 8.dp)
                )
            }
        ) {
            Text(
                text  = "Content",
                color = Color.White
            )
        }
    }
}