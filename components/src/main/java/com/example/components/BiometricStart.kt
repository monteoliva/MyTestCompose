package com.example.components

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

import com.example.components.biometric.BiometricAuthenticate

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
        .setConfirmationRequired(true)
        .build()

    BiometricAuthenticate(
        promptInfo      = promptInfo,
        onAuthSucceeded = { cryptoObj ->
            cryptoObj.hashCode()
        },
        onAuthError     = { _ ->
        }
    )
}