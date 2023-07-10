package com.aayar94.passwordgenerator.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aayar94.passwordgenerator.component.RowLayoutSavedPassword
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme


@Composable
fun SavedPasswordsScreen(navController: NavController, passwordList: List<SavedPasswordModel>) {
    PasswordGeneratorTheme {
        TODO("add function fetch password list from db")
        SavedPasswordScreenContent(passwordList)
    }
}

@Composable
fun SavedPasswordScreenContent(
    passwordList: List<SavedPasswordModel>,
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = passwordList,
            key = { password ->
                password.password.toString()
            }
        ) { password ->
            RowLayoutSavedPassword(password = password)
        }
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_4_XL)
@Preview(showSystemUi = true, device = Devices.PIXEL_4_XL, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SavedPasswordScreenPreview() {
    val mockList = mutableListOf<SavedPasswordModel>()
    repeat(10) {
        mockList.add(SavedPasswordModel("skjdhaskldja", "dljsahdla"))
    }
    SavedPasswordsScreen(rememberNavController(), mockList)
}
