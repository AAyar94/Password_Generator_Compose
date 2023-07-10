package com.aayar94.passwordgenerator.ui.navigation

enum class PasswordGeneratorScreens {
    PasswordGeneratorScreen,
    SavedPasswordsScreen;

    companion object {
        fun fromRoute(route: String?): PasswordGeneratorScreens = when (route?.substringBefore("/")) {
            PasswordGeneratorScreen.name -> PasswordGeneratorScreen
            SavedPasswordsScreen.name -> SavedPasswordsScreen
            null -> PasswordGeneratorScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}