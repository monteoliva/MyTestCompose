package com.example.mytestcompose.ui.components.biometric

import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class BiometricPromptContainerState {
    private lateinit var _cryptoObject: BiometricPrompt.CryptoObject
    private lateinit var _promptInfo  : BiometricPrompt.PromptInfo

    val promptInfo  : BiometricPrompt.PromptInfo   by lazy { _promptInfo   }
    val cryptoObject: BiometricPrompt.CryptoObject by lazy { _cryptoObject }

    private val _isPromptToShow = mutableStateOf(false)
    val isPromptToShow: State<Boolean> = _isPromptToShow

    fun authenticate(promptInfo: BiometricPrompt.PromptInfo, cryptoObject: BiometricPrompt.CryptoObject){
        _promptInfo           = promptInfo
        _cryptoObject         = cryptoObject;
        _isPromptToShow.value = true
    }

    fun resetShowFlag() { _isPromptToShow.value = false }
}