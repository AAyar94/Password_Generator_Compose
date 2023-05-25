package com.aayar94.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import com.aayar94.passwordgenerator.model.password.PasswordGenerator
import com.aayar94.passwordgenerator.model.password.content.CustomPwdContent
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme

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
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(id = R.string.app_name),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = generatedPassword.ifEmpty { stringResource(id = R.string.click_on_generate) },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(
                        onClick = {
                            val clipboardManager =
                                getSystemService(
                                    context,
                                    ClipboardManager::class.java
                                ) as ClipboardManager
                            val clipData = ClipData.newPlainText("text", generatedPassword)
                            clipboardManager.setPrimaryClip(clipData)

                            Toast.makeText(context, "Password Copied", Toast.LENGTH_LONG).show()
                        },
                        enabled = generatedPassword.isNotEmpty(), shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(stringResource(id = R.string.copy))
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
                EditableCheckbox(
                    value = customPasswordSetting,
                    onCheckChange = {
                        if (!customPasswordSetting.isNotEmpty()) {
                            isCustom = !isCustom
                        }
                    },
                    onValueChanged = {
                        if (it.isEmpty()) {
                            isCustom = false
                        }
                        customPasswordSetting = it
                    },
                    isChecked = isCustom
                )
                PasswordSize(
                    passwordSize = passwordSize,
                    onValueChange = {
                        if ((passwordSize.isNotEmpty() && passwordSize.toInt() < 200) || it.isEmpty()) {
                            passwordSize = it
                        }
                    }
                )

                Button(
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        val pwdGenerator = PasswordGenerator.Builder()
                            .addUpper(isUpper)
                            .addLower(isLower)
                            .addNumeric(isNumeric)
                            .addCustom(isCustom, CustomPwdContent(customPasswordSetting))
                            .setSize(if (passwordSize.isEmpty()) 8 else passwordSize.toInt())
                            .build()

                        generatedPassword = pwdGenerator.generatePassword()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = (isUpper || isLower || isCustom || isNumeric) && passwordSize.isNotEmpty() && passwordSize.toInt() > 0
                ) {
                    Text(text = stringResource(id = R.string.generate))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordSize(passwordSize: String, onValueChange: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = R.string.password_size),
            modifier = Modifier.weight(1f)
        )
        TextField(
            value = passwordSize,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("Max 200") },
            modifier = Modifier.weight(1f),
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
        Text(text = label)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableCheckbox(
    value: String,
    onCheckChange: () -> Unit,
    onValueChanged: (String) -> Unit,
    isChecked: Boolean,
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onCheckChange)
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        TextField(value = value, onValueChange = onValueChanged)
    }
}

@Preview(device = Devices.PIXEL_4_XL)
@Composable
fun PasswordGeneratorPreview() {
    PasswordGeneratorTheme {
        PasswordGenerator()
    }
}