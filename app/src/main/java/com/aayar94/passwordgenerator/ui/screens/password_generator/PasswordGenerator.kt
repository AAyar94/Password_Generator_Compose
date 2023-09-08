package com.aayar94.passwordgenerator.ui.screens.password_generator

import PasswordSizerSlider
import android.content.ClipData
import android.content.ClipboardManager
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.GeneratingTokens
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aayar94.passwordgenerator.R
import com.aayar94.passwordgenerator.component.EditableCheckBox
import com.aayar94.passwordgenerator.component.LabeledCheckbox
import com.aayar94.passwordgenerator.component.PasswordSaveAlertDialog
import com.aayar94.passwordgenerator.model.password.PasswordGenerator
import com.aayar94.passwordgenerator.model.password.content.CustomPwdContent
import com.aayar94.passwordgenerator.ui.navigation.PasswordGeneratorScreens
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PasswordGeneratorScreen(
    navController: NavController,
    viewModel: PasswordGeneratorViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colorScheme.surface)
    systemUiController.statusBarDarkContentEnabled = !isSystemInDarkTheme()
    PasswordGeneratorTheme {
        PasswordGeneratorUI(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordGeneratorUI(navController: NavController, viewModel: PasswordGeneratorViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var generatedPassword: String by remember { mutableStateOf("") }
    var passwordSize: String by remember { mutableStateOf("12") }
    var customPasswordSetting by remember { mutableStateOf("?!@,-_&#()[]{}") }
    var isUpper by remember { mutableStateOf(true) }
    var isLower by remember { mutableStateOf(true) }
    var isNumeric by remember { mutableStateOf(false) }
    var isCustom by remember { mutableStateOf(true) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(id = R.string.generate_a_password),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 48.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = generatedPassword.ifEmpty { stringResource(id = R.string.click_on_generate) },
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = {
                        val clipboardManager = ContextCompat.getSystemService(
                            context, ClipboardManager::class.java
                        ) as ClipboardManager
                        val clipData = ClipData.newPlainText("text", generatedPassword)
                        clipboardManager.setPrimaryClip(clipData)

                        Toast.makeText(context, "Password Copied", Toast.LENGTH_SHORT).show()
                    },
                    enabled = generatedPassword.isNotEmpty(),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = stringResource(
                            id = R.string.copy
                        ), tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        stringResource(id = R.string.copy),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
        if (showDialog) {
            Dialog(
                onDismissRequest = {
                    showDialog = false // Dismiss the custom AlertDialog
                },
                content = {
                    PasswordSaveAlertDialog(
                        passwordText = generatedPassword,
                        viewModel,
                        onDismissClicked = { showDialog = false }
                    )
                },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LabeledCheckbox(
            label = stringResource(id = R.string.upper_case), onCheckChange = {
                isUpper = !isUpper
            }, isChecked = isUpper
        )
        LabeledCheckbox(
            label = stringResource(id = R.string.lower_case), onCheckChange = {
                isLower = !isLower
            }, isChecked = isLower
        )
        LabeledCheckbox(
            label = stringResource(id = R.string.numeric), onCheckChange = {
                isNumeric = !isNumeric
            }, isChecked = isNumeric
        )
        EditableCheckBox(value = customPasswordSetting, onCheckChange = {
            if (customPasswordSetting.isNotEmpty()) isCustom = !isCustom
        }, isChecked = isCustom, onValueChange = {
            if (it.isEmpty()) {
                isCustom = false
            }
            customPasswordSetting = it
        })
        PasswordSizerSlider(
            passwordSize = passwordSize.toFloat(),
            8f,
            16f,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            if ((it.toString().isNotEmpty() && it.toInt() < 17) || it.toString().isEmpty()) {
                passwordSize = it.toString()
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        ElevatedButton(
            shape = RoundedCornerShape(15.dp),
            onClick = {
                val pwdGen =
                    PasswordGenerator.Builder()
                        .addUpper(isUpper).addLower(isLower).addNumeric(isNumeric)
                        .addCustom(isCustom, CustomPwdContent(customPasswordSetting))
                        .setSize(
                            if (passwordSize.isEmpty()) {
                                12
                            } else {
                                passwordSize.toFloat().toInt()
                            }
                        )
                        .build()

                generatedPassword = pwdGen.generatePassword()
            },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = (isUpper || isLower || isCustom || isNumeric) && passwordSize.isNotEmpty() && (passwordSize.toFloat()
                .toInt() > 0),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                imageVector = Icons.Default.GeneratingTokens,
                contentDescription = stringResource(R.string.generate_button),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(id = R.string.generate),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        FilledTonalButton(
            onClick = {
                showDialog = true
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(),
            enabled = generatedPassword.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = stringResource(R.string.save_password_button),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.save_password),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        FilledTonalButton(
            onClick = { navController.navigate(PasswordGeneratorScreens.SavedPasswordsScreen.name) },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = stringResource(R.string.saved_password_list_button),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.saved_password_list),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PasswordGeneratorPreview() {
    PasswordGeneratorScreen(rememberNavController())
}