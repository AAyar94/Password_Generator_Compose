package com.aayar94.passwordgenerator.ui.screens.saved_passwords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aayar94.passwordgenerator.component.RowLayoutSavedPassword
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme

@Composable
fun SavedPasswordsScreen(
    navController: NavController,
    viewModel: SavedPasswordsViewModel = hiltViewModel()
) {
    var passwordList = mutableListOf<SavedPasswordModel>()
    LaunchedEffect(key1 = "key1", block = {
        viewModel.getAllPasswords()
        if (!viewModel.passwordList.value.isNullOrEmpty()) {
            passwordList = (viewModel.passwordList.value as MutableList<SavedPasswordModel>?)!!
        }
    })
    PasswordGeneratorTheme {
        SavedPasswordsContent(passwordList, Modifier, viewModel)
    }
}

@Composable
fun SavedPasswordsContent(
    list: List<SavedPasswordModel>,
    modifier: Modifier = Modifier,
    viewModel: SavedPasswordsViewModel
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp)
        ) {
            Text(
                text = "Saved Passwords",
                fontSize = 36.sp,
                modifier = modifier
                    .align(CenterHorizontally)
                    .padding(top = 16.dp), color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (list.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Inbox,
                            contentDescription = "Saved password list empty image",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "There are no saved password",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                }
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        contentPadding = PaddingValues(12.dp),
                    ) {
                        items(list) { password ->
                            RowLayoutSavedPassword(password = password) {
                                viewModel.deletePassword(password)
                            }
                        }
                    }
                }
            }
        }
    }
}


