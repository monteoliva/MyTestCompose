package com.example.components.biometric

import androidx.biometric.BiometricManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun isValidBiometric() : Boolean {
    val context              = LocalContext.current
    val biometricManager     = remember { BiometricManager.from(context) }
    val isBiometricAvailable = remember {
        biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
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