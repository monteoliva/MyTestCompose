package com.example.mytestcompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

import com.example.mytestcompose.R
import com.example.mytestcompose.ui.theme.MyTestComposeTheme

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    label:       String = "",
    text:        String = "",
    placeholder: String = "",
    onValueChange: (String) -> Unit = {},
    onDone:              () -> Unit = {},
    enabled: Boolean = true,
    borderColor: Color = MaterialTheme.colorScheme.onSecondary,
    icon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions? = KeyboardOptions(imeAction = ImeAction.Done),
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    if (keyboardOptions != null) {
        OutlinedTextField(
            value         = text,
            onValueChange = onValueChange,
            label         = { Text(text = label, color = MaterialTheme.colorScheme.onSecondary) },
            enabled       = enabled,
            modifier      = modifier,
            placeholder   = { Text(text = placeholder, color = MaterialTheme.colorScheme.onSecondary) },
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

@Composable
fun TextFieldPasswordComponent(
    modifier: Modifier = Modifier,
    label:       String = "",
    text:        String = "",
    placeholder: String  = "",
    onValueChange: (String) -> Unit = {},
    onDone:              () -> Unit = {},
    enabled: Boolean = true,
    borderColor: Color = MaterialTheme.colorScheme.onSecondary,
    keyboardOptions: KeyboardOptions? = KeyboardOptions(imeAction = ImeAction.Done)
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordVisible    = rememberSaveable { mutableStateOf(false) }

    if (keyboardOptions != null) {
        OutlinedTextField(
            modifier             = modifier,
            value                = text,
            onValueChange        = { onValueChange.invoke(it) },
            label                = { Text(text = label, color = MaterialTheme.colorScheme.onSecondary) },
            enabled              = enabled,
            placeholder          = { Text(text = placeholder, color = MaterialTheme.colorScheme.onSecondary) },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            colors        = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor  = borderColor,
                focusedBorderColor = borderColor,
                focusedLabelColor  = borderColor,
                cursorColor        = borderColor
            ),
            trailingIcon    = {
                val image = if (passwordVisible.value)
                     painterResource(id = R.drawable.ic_visibility)
                else painterResource(id = R.drawable.ic_visibility_off)

                val description = if (passwordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible.value = passwordVisible.value.not()}){
                    Icon(
                        painter            = image,
                        contentDescription = description,
                        tint               = MaterialTheme.colorScheme.onSecondary
                    )
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onDone.invoke()
                }
            ),
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
    val text       = remember { mutableStateOf("") }
        text.value = "Valor do campo"
    MyTestComposeTheme {
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