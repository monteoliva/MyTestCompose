package com.example.components

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

import com.example.components.core.theme.ComponentComposeTheme

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    label:       String = "",
    text:        String = "",
    placeholder: String = "",
    onValueChange: (String) -> Unit = {},
    onDone:              () -> Unit = {},
    onNext:              () -> Unit = {},
    enabled: Boolean = true,
    borderColor: Color = MaterialTheme.colorScheme.onSecondary,
    icon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions? = KeyboardOptions(imeAction = ImeAction.Done),
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (keyboardOptions != null) {
        OutlinedTextField(
            value         = text,
            onValueChange = onValueChange,
            label         = { Text(text = label, color = MaterialTheme.colorScheme.onSecondary) },
            enabled       = enabled,
            modifier      = modifier
                .onFocusChanged {
                    if (it.hasFocus) { keyboardController?.show() }
                },
            visualTransformation = visualTransformation,
            placeholder          = {
                Text(
                    text  = placeholder,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
            colors               = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor  = borderColor,
                focusedBorderColor = borderColor,
                focusedLabelColor  = borderColor,
                cursorColor        = borderColor
            ),
            trailingIcon    = icon,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onDone.invoke()
                },
                onNext = {
                    keyboardController?.hide()
                    onNext.invoke()
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
private fun TextFieldComponentPreview() {
    val text = remember { mutableStateOf("Valor do campo") }
    ComponentComposeTheme {
        TextFieldComponent(
            label           = "Campo Teste",
            text            = text.value,
            placeholder     = "Digite o text",
            onValueChange   = { text.value = it },
            keyboardOptions = KeyboardOptions(
                keyboardType   = KeyboardType.Text,
                imeAction      = ImeAction.Done,
                autoCorrect    = true,
                capitalization = KeyboardCapitalization.Words
            )
        )
    }
}