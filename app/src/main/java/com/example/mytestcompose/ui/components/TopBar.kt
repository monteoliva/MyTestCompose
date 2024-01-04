package com.example.mytestcompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import com.example.mytestcompose.R
import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    subTitle: String,
    onNavigationClick: () -> Unit = {},
    onChangeClick:     () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        colors   = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector        = Icons.Filled.Menu,
                    contentDescription = "Navigation icon",
                    tint               = MaterialTheme.colorScheme.surfaceTint
                )
            }
        },
        title   = {
            Column {
                Text(
                    text  = title, //stringResource(R.string.app_name),
                    color = MaterialTheme.colorScheme.surfaceTint,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text  = subTitle,
                    color = MaterialTheme.colorScheme.surfaceTint,
                    style = MaterialTheme.typography.titleMedium
                )

            }
        },
        actions = {
            IconButton(onClick = onChangeClick) {
                Icon(
                    painter            = painterResource(id = R.drawable.ic_change),
                    contentDescription = "Change Mode",
                    tint               = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }
    )
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
private fun TopBarPreview() {
    MyTestComposeTheme {
        TopBar(
            title    = "Title",
            subTitle = "SubTitle"
        )
    }
}
