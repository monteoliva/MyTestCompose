package com.example.mytestcompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalComponent3(
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit),
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() },
        sheetState       = modalBottomSheetState,
        containerColor   = Color.White,
        tonalElevation   = 20.dp,
        dragHandle       = { BottomSheetDefaults.DragHandle() }
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .wrapContentHeight()
        ) {
            Column(content = content)
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
fun ModalComponent3Preview() {
    MyTestComposeTheme {
        ModalComponent3(onDismissRequest = {}) {
            CountryList()
        }
    }
}
