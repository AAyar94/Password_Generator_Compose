package com.aayar94.passwordgenerator.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayar94.passwordgenerator.model.db.SavedPasswordModel
import com.aayar94.passwordgenerator.ui.theme.PasswordGeneratorTheme

@Composable
fun RowLayoutSavedPassword(
    password: SavedPasswordModel,
    deleteButtonOnClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Transparent)
            .padding(4.dp)
    ) {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = password.password.toString(), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = password.tag.toString(), fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { deleteButtonOnClick() },
                modifier = Modifier
                    .wrapContentSize()
                    .clip(
                        CircleShape
                    )
                    .align(Alignment.CenterVertically)
                    .padding(12.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SavedPasswordPreview() {
    val mockData = SavedPasswordModel(0, "asdfgh", "tag1")
    PasswordGeneratorTheme {
        RowLayoutSavedPassword(password = mockData) {   /* TODO */ }
    }
}