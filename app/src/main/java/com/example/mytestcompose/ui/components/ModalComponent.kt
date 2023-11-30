package com.example.mytestcompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ModalComponent(
    sheetState: ModalBottomSheetState,
    isShowDragHandle: Boolean = true,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalBottomSheetLayout(
        sheetState           = sheetState,
        sheetShape           = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
        sheetGesturesEnabled = true,
        sheetElevation       = 20.dp,
        sheetBackgroundColor = MaterialTheme.colorScheme.onBackground,
        sheetContent         = {
            if (isShowDragHandle) {
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BottomSheetDefaults.DragHandle(width = 100.dp)
                }
            }
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
    val showModal = rememberModalBottomSheetState(
        initialValue       = ModalBottomSheetValue.Expanded,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded   = false
    )
    MyTestComposeTheme {
        ModalComponent (
            sheetState       = showModal,
            isShowDragHandle = true
        ) { CountryList() }
    }
}
