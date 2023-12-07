package com.example.mytestcompose.ui.feature

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

import com.example.mytestcompose.R
import com.example.mytestcompose.ui.components.biometric.BiometricAuthenticate

@Composable
fun BiometricStart(
    title     : String = stringResource(id = R.string.authenticationTitle),
    subTitle  : String = stringResource(id = R.string.authenticationSubTitle),
    buttonText: String = stringResource(id = R.string.authenticationButtonText)
) {
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
        .setTitle(title)
        .setSubtitle(subTitle)
        .setNegativeButtonText(buttonText)
        .build()

    BiometricAuthenticate(
        promptInfo      = promptInfo,
        onAuthSucceeded = { cryptoObj ->
        },
        onAuthError     = { authErr ->
        }
    )
}