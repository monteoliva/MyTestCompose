package com.example.mytestcompose.ui.components.biometric

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@Composable
fun BiometricAuthenticate (
    promptInfo: BiometricPrompt.PromptInfo,
    onAuthSucceeded: (cryptoObject: BiometricPrompt.CryptoObject?) -> Unit = {},
    onAuthError: (AuthError) -> Unit = {}
) {
    val context         = LocalContext.current
    val executor        = ContextCompat.getMainExecutor(context)
    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onAuthError.invoke(AuthError(errorCode, errString.toString()))
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)


                onAuthSucceeded.invoke(result.cryptoObject)
            }
        }
    )

    biometricPrompt.authenticate(promptInfo)
}