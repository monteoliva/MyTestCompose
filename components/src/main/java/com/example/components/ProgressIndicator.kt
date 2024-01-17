package com.example.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import com.example.components.core.theme.ComponentComposeTheme
import com.example.components.core.theme.StyleTitle

@Composable
fun ProgressIndicator(
    text: String = "",
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = { onDismissRequest.invoke() },
        properties       = DialogProperties(
            dismissOnBackPress    = false,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier         = Modifier
                .wrapContentSize(align = Alignment.Center)
                .padding(all = 4.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 8.dp)
                )
        ) {
            Column {
//                CircularProgressIndicator()
//
                if (text.isNotEmpty()) {
                    Text(
                        modifier  = Modifier.fillMaxWidth().padding(top = 5.dp),
                        text      = text,
                        textAlign = TextAlign.Center
                    )
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
    showBackground = false,
    name           = "Dark Mode"
)
@Composable
private fun ProgressIndicatorPreview() {
    ComponentComposeTheme {
        ProgressIndicator (
            text = "Loading",
            onDismissRequest = {}
        )
    }
}