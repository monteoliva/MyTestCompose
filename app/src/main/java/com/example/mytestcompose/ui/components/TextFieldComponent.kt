package com.example.mytestcompose.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TextFieldComponent(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    enabled:     Boolean = true,
    placeholder: String  = "",
    borderColor: Color   = Color.DarkGray,
    icon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions? = KeyboardOptions(imeAction = ImeAction.Done),
    onDone: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (keyboardOptions != null) {
        OutlinedTextField(
            value         = text,
            onValueChange = onValueChange,
            label         = { Text(text = label) },
            enabled       = enabled,
            modifier      = modifier,
            placeholder   = { Text(text = placeholder) },
            colors        = TextFieldDefaults.outlinedTextFieldColors(
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
                }
            )
        )
    }
}