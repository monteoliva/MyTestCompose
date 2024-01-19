package com.example.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import com.example.components.core.theme.ComponentComposeTheme

@Composable
fun ProgressLinearIndicator(
    text: String = "",
    cornerRadius: Dp = 16.dp,
    total:    Float = 0.0f,
    progress: Float = 0.0f
) {
    val progressF: Float  = (progress / total)
    val progressS: String = "${progress.toInt()}/${total.toInt()}"

    Dialog(
        onDismissRequest = {},
        properties       = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .padding(all = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text     = text,
                style    = TextStyle(
                    color      = MaterialTheme.colorScheme.onSecondary,
                    fontSize   = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            LinearProgressIndicator(
                progress = progressF,
                color    = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .wrapContentSize(align = Alignment.Center)
                    .padding(top = 5.dp)
            )
            Text(
                modifier  = Modifier.fillMaxWidth().padding(top = 5.dp),
                text      = progressS,
                textAlign = TextAlign.End,
                style     = TextStyle(
                    color      = MaterialTheme.colorScheme.onSecondary,
                    fontSize   = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            )
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
private fun ProgressLinearIndicatorPreview() {
    ComponentComposeTheme {
        ProgressLinearIndicator(
            text     = "Progress Linear Indicator Teste",
            total    = 50.0f,
            progress = 10.0f
        )
    }
}