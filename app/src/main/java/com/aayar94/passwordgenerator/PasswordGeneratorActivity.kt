package com.aayar94.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import com.aayar94.passwordgenerator.model.password.PasswordGenerator
import com.aayar94.passwordgenerator.model.password.content.CustomPwdContent
import com.aayar94.passwordgenerator.ui.theme.AppTheme

class PasswordGeneratorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PasswordGenerator()
        }
    }
}

@Composable
fun PasswordGenerator() {
    var generatedPassword: String by remember { mutableStateOf("") }
    var passwordSize: String by remember { mutableStateOf("8") }
    var customPasswordSetting by remember { mutableStateOf("?!@,-_&#()[]{}") }
    var isUpper by remember { mutableStateOf(true) }
    var isLower by remember { mutableStateOf(true) }
    var isNumeric by remember { mutableStateOf(false) }
    var isCustom by remember { mutableStateOf(true) }
    val context = LocalContext.current
    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(id = R.string.generate_a_password),
                modifier = Modifier.align(CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(10.dp))

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
                    Button(
                        onClick = {
                            val clipboardManager = getSystemService(
                                context,
                                ClipboardManager::class.java
                            ) as ClipboardManager
                            val clipData = ClipData.newPlainText("text", generatedPassword)
                            clipboardManager.setPrimaryClip(clipData)

                            Toast.makeText(context, "Password Copied", Toast.LENGTH_SHORT).show()
                        },
                        enabled = generatedPassword.isNotEmpty(),
                        shape = RoundedCornerShape(15.dp)
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

            PasswordSize(
                passwordSize = passwordSize,
                onValueChange = {
                    if ((it.isNotEmpty() && it.toInt() < 200) || it.isEmpty()) {
                        passwordSize = it
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    val pwdGen = PasswordGenerator.Builder()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordSize(passwordSize: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.password_size),
            modifier = Modifier.weight(1f)
        )
        TextField(
            value = passwordSize,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("Max 200") },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LabeledCheckbox(label: String, onCheckChange: () -> Unit, isChecked: Boolean) {
    Row(
        modifier = Modifier
            .clickable(onClick = onCheckChange)
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableCheckBox(
    value: String,
    onCheckChange: () -> Unit,
    onValueChange: (String) -> Unit,
    isChecked: Boolean,
) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = onCheckChange
            )
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(modifier = Modifier.width(12.dp))
        TextField(value = value, onValueChange = onValueChange)
    }
}

@Preview(device = Devices.PIXEL_4_XL)
@Composable
fun PasswordGeneratorPreview() {
    AppTheme {
        PasswordGenerator()
    }
}