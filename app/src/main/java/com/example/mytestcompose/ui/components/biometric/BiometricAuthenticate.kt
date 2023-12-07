package com.example.mytestcompose.ui.components.biometric

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@Composable
fun isValidBiometric() : Boolean {
    val context              = LocalContext.current
    val biometricManager     = remember { BiometricManager.from(context) }
    val isBiometricAvailable = remember {
        biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
    }
    return when (isBiometricAvailable) {
        BiometricManager.BIOMETRIC_SUCCESS                        -> true
        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE              -> false
        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE           -> false
        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> false
        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED              -> false
        BiometricManager.BIOMETRIC_STATUS_UNKNOWN                 -> false
        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED            -> false
        else                                                      -> false
    }
}

@Composable
fun BiometricAuthenticate(
    isRead: MutableState<Boolean> = mutableStateOf(false),
    state: BiometricPromptContainerState,
    onAuthSucceeded: (cryptoObject: BiometricPrompt.CryptoObject?) -> Unit = {}
) {
    val context         = LocalContext.current
    val executor        = ContextCompat.getMainExecutor(context)
    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                state.resetShowFlag()
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                state.resetShowFlag()
                onAuthSucceeded.invoke(result.cryptoObject)
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setAllowedAuthenticators(BIOMETRIC_STRONG)
        .setTitle("Biometric Authentication")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Use account password")
        .build()


    if (state.isPromptToShow.value) {
        biometricPrompt.authenticate(promptInfo)
    }
}