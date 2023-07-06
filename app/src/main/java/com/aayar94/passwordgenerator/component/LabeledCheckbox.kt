package com.aayar94.passwordgenerator.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme

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
        Text(text = label, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LabeledCheckboxPreview(){
    PasswordGeneratorTheme {
        LabeledCheckbox(label = "Number", onCheckChange = { /*TODO*/ }, isChecked = true)
        LabeledCheckbox(label = "Number", onCheckChange = { /*TODO*/ }, isChecked = false)
    }
}
