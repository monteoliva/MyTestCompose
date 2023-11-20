package com.example.mytestcompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalComponent(
    visibility: ModalBottomSheetState,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheetLayout(
        sheetState           = visibility,
        sheetShape           = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetGesturesEnabled = true,
        sheetElevation       = 20.dp,
        sheetContent         = {
            content()
        }
    ) {}
}

@OptIn(ExperimentalMaterialApi::class)
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
fun ModalComponentPreview() {
    val showModal = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    MyTestComposeTheme {
        ModalComponent (visibility = showModal) {
            Column(
                modifier            = Modifier.fillMaxWidth().padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Test Modal Example")
            }
        }
    }
}
