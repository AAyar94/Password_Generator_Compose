import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aayar94.passwordgenerator.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordSizerSlider(
    passwordSize: Float,
    bottomLimit: Float,
    topLimit: Float,
    modifier: Modifier,
    onValueChange: (Float) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.password_size),
            color = MaterialTheme.colorScheme.onBackground
        )
        Slider(
            value = passwordSize,
            onValueChange = onValueChange,
            valueRange = bottomLimit..topLimit,
            steps = (topLimit-bottomLimit-1).toInt(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text(
            text = stringResource(R.string.selected_size_min_8_max_16, passwordSize.toInt()),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PasswordSizeSlider() {
    PasswordSizerSlider(
        passwordSize = 12F,
        8f,
        16f,
        modifier=Modifier,
        onValueChange = {},
    )
}
