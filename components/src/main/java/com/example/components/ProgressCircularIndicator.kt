package com.example.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import com.example.components.core.theme.ComponentComposeTheme

@Composable
fun ProgressCircularIndicator(
    cornerRadius: Dp = 16.dp
) {
    Dialog(
        onDismissRequest = {},
        properties       = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(cornerRadius))
                .padding(all = 20.dp), // inner padding
        ) {
            CircularProgressIndicator(
                modifier    = Modifier.wrapContentSize(align = Alignment.Center),
                color       = MaterialTheme.colorScheme.secondary,
                strokeWidth = 2.dp
            )

            Text(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                text     = "Please wait...",
                style    = TextStyle(
                    color      = Color.Black,
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
private fun ProgressCircularIndicatorPreview() {
    ComponentComposeTheme {
        ProgressCircularIndicator()
    }
}