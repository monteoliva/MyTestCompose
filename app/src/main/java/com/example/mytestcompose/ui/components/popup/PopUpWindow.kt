package com.example.mytestcompose.ui.components.popup

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@Composable
fun PopUpWindow(
    title: String,
    isShowTitle: Boolean = true,
    padding: Dp = 20.dp,
    onDismiss: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val cornerSize = 16.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.8f))
    ) {
        Popup(
            alignment        = Alignment.Center,
            onDismissRequest = { onDismiss.invoke() }
        ) {
            Box(
                modifier = Modifier
                    .padding(all = padding)
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(cornerSize)
                    )
            )
            {
                Column {
                    if (isShowTitle) {
                        PopUpWindowTop(
                            cornerSize = cornerSize,
                            title      = title
                        ) {
                            onDismiss.invoke()
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    content()
                }
            }
        }
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
fun PopUpWindowPreview() {
    MyTestComposeTheme {
        PopUpWindow (
            title     = "Teste",
            onDismiss = {}
        ) {
            Text(text = "Teste de Content")
        }
    }
}
