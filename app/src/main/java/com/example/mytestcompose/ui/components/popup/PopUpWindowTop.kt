package com.example.mytestcompose.ui.components.popup

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import com.example.mytestcompose.ui.theme.MyTestComposeTheme
import com.example.mytestcompose.ui.theme.StyleTitle

@Composable
fun PopUpWindowTop(
    cornerSize: Dp,
    title: String,
    onDismiss: () -> Unit
) {
    Row(
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier              = Modifier
            .background(
                color = MaterialTheme.colorScheme.onTertiary,
                shape = RoundedCornerShape(
                    topStart = cornerSize,
                    topEnd   = cornerSize
                )
            )
            .fillMaxWidth()
            .padding(
                top    = 14.dp,
                start  = 10.dp,
                end    =  8.dp,
                bottom = 14.dp
            )
    )
    {
        Text(
            text  = title,
            color = Color.White,
            style = StyleTitle
        )

        Icon(
            imageVector        = Icons.Default.Close,
            tint               = Color.White,
            contentDescription = null,
            modifier           = Modifier
                .clickable { onDismiss.invoke() }
                .padding(end = 4.dp)
        )
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode         = Configuration.UI_MODE_NIGHT_YES,
    showBackground = false,
    name           = "Dark Mode"
)
@Composable
fun PreviewTopPopUpWindow() {
    MyTestComposeTheme {
        PopUpWindowTop(
            cornerSize = 16.dp,
            title      = "Title test"
        ) {}
    }
}