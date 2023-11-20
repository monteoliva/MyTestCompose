package com.example.mytestcompose.ui.feature

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

import com.example.mytestcompose.ui.components.ModalComponent
import com.example.mytestcompose.ui.components.expandable.ExpandableSwipeCardItem
import com.example.mytestcompose.ui.components.popup.PopUpWindow
import com.example.mytestcompose.ui.theme.MyTestComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color    = MaterialTheme.colorScheme.background
                ) {
                    GreetingView("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GreetingView(name: String) {
    val coroutineScope = rememberCoroutineScope()
    val showModal      = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val openDialog     = rememberSaveable { mutableStateOf(false) }
    val openModal      = rememberSaveable { mutableStateOf(false) }

    Box(
        modifier         = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Button(onClick = { openDialog.value = true  }) {
                Text(text = "Popwindow Open")
            }

            Button(onClick = { openModal.value = true }) {
                Text(text = "Modal Button Open")
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
        }
    }
    if (openModal.value) {
        openModal.value = false
        coroutineScope.launch { showModal.show() }
    }
    if (openDialog.value) {
        PopUpWindow(
            title     = "Janela",
            onDismiss = { openDialog.value = false }
        ) {
            Text(
                text     = name,
                modifier = Modifier.padding(all = 32.dp)
            )
        }
    }

    ModalComponent(visibility  = showModal) {
        Column(
            modifier            = Modifier.fillMaxWidth().padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Test Modal Example")
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
fun GreetingPreview() {
    MyTestComposeTheme {
        GreetingView("Android")
    }
}