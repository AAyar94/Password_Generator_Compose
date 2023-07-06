package com.aayar94.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.core.content.ContextCompat
import com.aayar94.passwordgenerator.component.EditableCheckBox
import com.aayar94.passwordgenerator.component.LabeledCheckbox
import com.aayar94.passwordgenerator.component.PasswordSizer
import com.aayar94.passwordgenerator.model.password.content.CustomPwdContent
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme

@Composable
fun PasswordGeneratorScreen() {
    PasswordGeneratorTheme {
        PasswordGeneratorUI()
    }
}

@Composable
fun PasswordGeneratorUI() {
    var generatedPassword: String by remember { mutableStateOf("") }
    var passwordSize: String by remember { mutableStateOf("8") }
    var customPasswordSetting by remember { mutableStateOf("?!@,-_&#()[]{}") }
    var isUpper by remember { mutableStateOf(true) }
    var isLower by remember { mutableStateOf(true) }
    var isNumeric by remember { mutableStateOf(false) }
    var isCustom by remember { mutableStateOf(true) }
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(id = R.string.generate_a_password),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineMedium
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
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    FilledTonalButton(
                        onClick = {
                            val clipboardManager = ContextCompat.getSystemService(
                                context,
                                ClipboardManager::class.java
                            ) as ClipboardManager
                            val clipData = ClipData.newPlainText("text", generatedPassword)
                            clipboardManager.setPrimaryClip(clipData)

                            Toast.makeText(context, "Password Copied", Toast.LENGTH_SHORT).show()
                        },
                        enabled = generatedPassword.isNotEmpty(),
                        shape = RoundedCornerShape(15.dp),

                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = stringResource(
                                id = R.string.copy
                            )
                        )
                        Text(stringResource(id = R.string.copy))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            LabeledCheckbox(
                label = stringResource(id = R.string.upper_case),
                onCheckChange = {
                    isUpper = !isUpper
                },
                isChecked = isUpper
            )
            LabeledCheckbox(
                label = stringResource(id = R.string.lower_case),
                onCheckChange = {
                    isLower = !isLower
                },
                isChecked = isLower
            )
            LabeledCheckbox(
                label = stringResource(id = R.string.numeric),
                onCheckChange = {
                    isNumeric = !isNumeric
                },
                isChecked = isNumeric
            )
            EditableCheckBox(
                value = customPasswordSetting,
                onCheckChange = {
                    if (customPasswordSetting.isNotEmpty())
                        isCustom = !isCustom
                },
                isChecked = isCustom,
                onValueChange = {
                    if (it.isEmpty()) {
                        isCustom = false
                    }
                    customPasswordSetting = it
                }
            )

            PasswordSizer(
                passwordSize = passwordSize,
                onValueChange = {
                    if ((it.isNotEmpty() && it.toInt() < 200) || it.isEmpty()) {
                        passwordSize = it
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            FilledTonalButton(
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    val pwdGen =
                        com.aayar94.passwordgenerator.model.password.PasswordGenerator.Builder()
                            .addUpper(isUpper)
                            .addLower(isLower)
                            .addNumeric(isNumeric)
                            .addCustom(isCustom, CustomPwdContent(customPasswordSetting))
                            .setSize(if (passwordSize.isEmpty()) 8 else passwordSize.toInt())
                            .build()

                    generatedPassword = pwdGen.generatePassword()

                },
                modifier = Modifier.fillMaxWidth(),
                enabled = (isUpper || isLower || isCustom || isNumeric) && passwordSize.isNotEmpty() && passwordSize.toInt() > 0
            ) {
                Text(text = stringResource(id = R.string.generate))
            }

        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PasswordGeneratorPreview() {
    PasswordGeneratorScreen()
}