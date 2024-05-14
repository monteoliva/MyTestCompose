package com.example.mytestcompose.ui.feature

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController

import com.example.mytestcompose.R
import com.example.mytestcompose.ui.components.NavigationComponent
import com.example.mytestcompose.ui.components.TopBar
import com.example.mytestcompose.ui.theme.MyTestComposeTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color    = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MyScreen(isPreview: Boolean = false) {
    val isSystem      = isSystemInDarkTheme()
    val isDarkTheme   = remember { mutableStateOf(isSystem) }
    val subTitle      = remember { mutableStateOf(value = "Home") }
    val navController = rememberNavController()

    MyTestComposeTheme(darkTheme = isDarkTheme.value) {
        Scaffold(
            topBar = {
                TopBar(
                    title             = stringResource(R.string.app_name),
                    subTitle          = subTitle.value,
                    onNavigationClick = {},
                    onChangeClick     = {
                        isDarkTheme.value = isDarkTheme.value.not()
                    }
                )
            },
            bottomBar = {}
        ) {
            NavigationComponent(
                isPreview     = isPreview,
                navController = navController
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
private fun MyScreenPreview() {
    MyTestComposeTheme {
        MyScreen(isPreview = true)
    }
}