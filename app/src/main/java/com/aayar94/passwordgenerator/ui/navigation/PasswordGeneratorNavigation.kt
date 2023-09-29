package com.aayar94.passwordgenerator.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.passwordgenerator.ui.screens.password_generator.PasswordGeneratorScreen
import com.aayar94.passwordgenerator.ui.screens.saved_passwords.SavedPasswordsScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PasswordGeneratorNavigation() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colorScheme.surface)
    systemUiController.statusBarDarkContentEnabled = !isSystemInDarkTheme()
    NavHost(
        navController,
        startDestination = PasswordGeneratorScreens.PasswordGeneratorScreen.name,
        builder = {
            composable(PasswordGeneratorScreens.PasswordGeneratorScreen.name) {
                PasswordGeneratorScreen(navController = navController)
            }
            composable(PasswordGeneratorScreens.SavedPasswordsScreen.name) {
                SavedPasswordsScreen(navController)
            }

        })
}