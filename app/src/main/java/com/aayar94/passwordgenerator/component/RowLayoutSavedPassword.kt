package com.aayar94.passwordgenerator.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel

@Composable
fun RowLayoutSavedPassword(
    password: SavedPasswordModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = password.password.toString(), fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = password.tag.toString(), fontSize = 13.sp)
        }
    }
}


@Preview(showBackground = false)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = false)
@Composable
fun SavedPasswordPreview() {
    val mockData = SavedPasswordModel("asdfgh", "tag1")
    RowLayoutSavedPassword(password = mockData)
}