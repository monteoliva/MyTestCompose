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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    label: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled:     Boolean = true,
    placeholder: String  = "",
    borderColor: Color   = MaterialTheme.colorScheme.onSecondary,
    icon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions? = KeyboardOptions(imeAction = ImeAction.Done),
    onDone: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (keyboardOptions != null) {
        OutlinedTextField(
            value         = value,
            onValueChange = {},
            label         = { Text(text = label, color = MaterialTheme.colorScheme.onSecondary) },
            enabled       = enabled,
            modifier      = modifier.fillMaxWidth(),
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
            )
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
fun TextFieldComponentPreview() {
    MyTestComposeTheme {
        TextFieldComponent(
            label       = "Campo Teste",
            value       = "Valor do campo",
            placeholder = "Digite o text"
        )
    }
}