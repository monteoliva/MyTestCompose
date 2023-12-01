package com.example.mytestcompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    onValueChange: (String) -> Unit = {},
    enabled:     Boolean = true,
    focusable:   Boolean = false,
    placeholder: String  = "",
    borderColor: Color   = MaterialTheme.colorScheme.onSecondary,
    icon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions? = KeyboardOptions(imeAction = ImeAction.Done),
    onDone: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester     = remember { FocusRequester() }

    if (keyboardOptions != null) {
        OutlinedTextField(
            value         = text,
            onValueChange = { onValueChange.invoke(it) },
            label         = { Text(text = label, color = MaterialTheme.colorScheme.onSecondary) },
            enabled       = enabled,
            placeholder   = { Text(text = placeholder) },
            colors        = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor  = borderColor,
                focusedBorderColor = borderColor,
                focusedLabelColor  = borderColor,
                cursorColor        = borderColor,
                backgroundColor    = MaterialTheme.colorScheme.onBackground,
                textColor          = MaterialTheme.colorScheme.onSecondary
            ),
            trailingIcon    = icon,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onDone.invoke()
                }
            ),
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) { keyboardController?.show() }
                }
        )
        LaunchedEffect(Unit) {
            if (focusable) { focusRequester.requestFocus() }
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
fun TextFieldComponentPreview() {
    val text       = remember { mutableStateOf("") }
        text.value = "Valor do campo"
    MyTestComposeTheme {
        TextFieldComponent(
            label         = "Campo Teste",
            text          = text.value,
            placeholder   = "Digite o text",
            onValueChange = { text.value = it }
        )
    }
}