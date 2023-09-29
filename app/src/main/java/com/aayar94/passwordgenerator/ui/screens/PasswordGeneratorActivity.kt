package com.aayar94.passwordgenerator.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.aayar94.passwordgenerator.ui.navigation.PasswordGeneratorNavigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordGeneratorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(MaterialTheme.colorScheme.surface)
            systemUiController.statusBarDarkContentEnabled = !isSystemInDarkTheme()
            PasswordGeneratorNavigation()
        }
    }
}

@Preview(device = Devices.PIXEL_4_XL)
@Preview(uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_4_XL)
@Composable
fun AppPreview() {
    PasswordGeneratorNavigation()
}