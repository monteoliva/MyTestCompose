package com.example.mytestcompose.ui.feature

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity

import com.example.mytestcompose.ui.theme.MyTestComposeTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartScreen()
        }
    }
}

@Composable
fun StartScreen() {
    val isSystem    = isSystemInDarkTheme()
    val isDarkTheme = remember { mutableStateOf(isSystem) }
    MyTestComposeTheme(
        darkTheme = isDarkTheme.value
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color    = MaterialTheme.colorScheme.background
        ) {
            MainScreen()
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
private fun GreetingPreview() {
    MyTestComposeTheme {
        MainScreen(isPreview = true)
    }
}