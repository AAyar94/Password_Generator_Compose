package com.aayar94.passwordgenerator

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme

@Composable
fun SavedPasswordsScreen(){
    PasswordGeneratorTheme {
        SavedPasswords()
    }
}

@Composable
fun SavedPasswords(){
    Column(modifier= Modifier.fillMaxSize()) {
        Row {
            Text(text = "Saved Passwords")
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SavedPasswordsPreview(){
    SavedPasswords()
}
