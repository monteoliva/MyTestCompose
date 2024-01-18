package com.example.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.components.core.theme.ComponentComposeTheme

@Composable
fun CountryList() {
    val countries = listOf(
        Pair("United States" , "\uD83C\uDDFA\uD83C\uDDF8"),
        Pair("Canada"        , "\uD83C\uDDE8\uD83C\uDDE6"),
        Pair("India"         , "\uD83C\uDDEE\uD83C\uDDF3"),
        Pair("Germany"       , "\uD83C\uDDE9\uD83C\uDDEA"),
        Pair("France"        , "\uD83C\uDDEB\uD83C\uDDF7"),
        Pair("Japan"         , "\uD83C\uDDEF\uD83C\uDDF5"),
        Pair("China"         , "\uD83C\uDDE8\uD83C\uDDF3"),
        Pair("Brazil"        , "\uD83C\uDDE7\uD83C\uDDF7"),
        Pair("Australia"     , "\uD83C\uDDE6\uD83C\uDDFA"),
        Pair("Russia"        , "\uD83C\uDDF7\uD83C\uDDFA"),
        Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
    )
    val isLoading = remember { mutableStateOf(true) }

    LazyColumn {
        items(countries) {(country, flag) ->
            ItemList(country = country, flag = flag)
        }
    }
}

@Composable
private fun ItemList(country: String, flag: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 10.dp,
                horizontal = 20.dp
            )
    ) {
        Text(
            text     = flag,
            modifier = Modifier.padding(end = 20.dp)
        )
        Text(
            text  = country,
            color = MaterialTheme.colorScheme.onSecondary
        )
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
private fun CountryListPreview() {
    ComponentComposeTheme {
        CountryList()
    }
}
