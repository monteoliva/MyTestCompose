package com.example.mytestcompose.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.mytestcompose.ui.feature.MainScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    isPreview: Boolean = false
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") { MainScreen(isPreview = isPreview) }
    }
}