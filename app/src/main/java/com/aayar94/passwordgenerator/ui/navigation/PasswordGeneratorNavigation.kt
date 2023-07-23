package com.aayar94.passwordgenerator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aayar94.passwordgenerator.ui.screens.PasswordGeneratorScreen
import com.aayar94.passwordgenerator.ui.screens.SavedPasswordsScreen

@Composable
fun PasswordGeneratorNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = PasswordGeneratorScreens.PasswordGeneratorScreen.name,
        builder = {
            composable(PasswordGeneratorScreens.PasswordGeneratorScreen.name) {
                PasswordGeneratorScreen(navController = navController)
            }
            composable(PasswordGeneratorScreens.SavedPasswordsScreen.name){
                SavedPasswordsScreen()
            }

        })
}