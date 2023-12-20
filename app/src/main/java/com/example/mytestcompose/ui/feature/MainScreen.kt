package com.example.mytestcompose.ui.feature

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

import com.example.mytestcompose.ui.components.CountryList
import com.example.mytestcompose.ui.components.ModalComponent
import com.example.mytestcompose.ui.components.TextFieldComponent
import com.example.mytestcompose.ui.components.biometric.isValidBiometric
import com.example.mytestcompose.ui.components.expandable.ExpandableSwipeCardItem
import com.example.mytestcompose.ui.components.popup.PopUpWindow
import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(isPreview: Boolean = false) {
    val coroutineScope       = rememberCoroutineScope()
    val openDialog           = rememberSaveable { mutableStateOf(false) }
    val openModal            = rememberSaveable { mutableStateOf(false) }
    val openBiometric        = rememberSaveable { mutableStateOf(false) }
    val text                 = remember         { mutableStateOf("") }
    val showModal            = rememberModalBottomSheetState(
        initialValue       = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded   = false
    )

    Box(
        modifier         = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier.padding(all = 10.dp)
        ) {
            Button(onClick = { openDialog.value = true  }) {
                Text(text = "PopUpWindow Open")
            }

            Button(onClick = { openModal.value = true }) {
                Text(text = "Modal Button Open")
            }

            if (!isPreview) {
                if (isValidBiometric()) {
                    Button(onClick = { openBiometric.value = true }) {
                        Text(text = "Open Biometric Authentication")
                    }
                }
            }

            ExpandableSwipeCardItem(
                shownContent = {
                    Text(
                        text     = "Teste de conteúdo",
                        modifier = Modifier.padding(all = 20.dp),
                        color    = Color.White
                    )
                },
                onSwipeLeft  = { openDialog.value = true },
                onSwipeRight = { openModal.value  = true }
            ) {
                Text(
                    text     = "Teste de restante do conteúdo",
                    modifier = Modifier.padding(all = 20.dp),
                    color    = Color.White
                )
            }
//            Row(modifier = Modifier.padding(top = 8.dp)) {
//                TextFieldComponent(
//                    label           = "Name",
//                    text            = text.value,
//                    placeholder     = "Digit your name here",
//                    onValueChange   = { text.value = it },
//                    onDone          = {},
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType   = KeyboardType.Text,
//                        imeAction      = ImeAction.Done,
//                        autoCorrect    = true,
//                        capitalization = KeyboardCapitalization.Words
//                    )
//                )
//            }
        }
    }
    if (openDialog.value) {
        PopUpWindow(
            title     = "Janela",
            onDismiss = { openDialog.value = false }
        ) {
            Text(
                text     = "Janela aberta",
                modifier = Modifier.padding(all = 32.dp)
            )
        }
    }

    if (openModal.value) {
        openModal.value = false
        coroutineScope.launch { showModal.show() }
//        ModalComponent3(
//            onDismissRequest = { openModal.value = false }
//        ) {
//            CountryList()
//        }
    }
    ModalComponent(sheetState = showModal) { CountryList() }

    if (openBiometric.value) {
        openBiometric.value = false
        BiometricStart()
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
private fun MainScreenPreview() {
    MyTestComposeTheme {
        MainScreen(isPreview = true)
    }
}