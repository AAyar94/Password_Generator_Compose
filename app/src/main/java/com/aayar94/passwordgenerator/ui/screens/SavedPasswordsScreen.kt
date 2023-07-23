package com.aayar94.passwordgenerator.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayar94.passwordgenerator.component.RowLayoutSavedPassword
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme

@Composable
fun SavedPasswordsScreen() {
    val passwordList: List<SavedPasswordModel> = emptyList()
    PasswordGeneratorTheme {
        SavedPasswordsContent(passwordList)
    }
}

@Composable
fun SavedPasswordsContent(list: List<SavedPasswordModel>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Saved Passwords",
                fontSize = 36.sp,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().background(Color.Transparent),
                contentPadding = PaddingValues(12.dp),
            ) {
                items(list) { password ->
                    RowLayoutSavedPassword(password = password)
                }
            }
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SavedPasswordsPreview() {
    val list = mutableListOf<SavedPasswordModel>()
    list.add(SavedPasswordModel(1, "asd", "bsd"))
    list.add(SavedPasswordModel(1, "asd", "bsd"))
    list.add(SavedPasswordModel(1, "asd", "bsd"))
    SavedPasswordsContent(list)
}
