package com.purewowstudio.bodystats.ui.overview.actions.connect

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun ConnectCard(isButtonEnabled: Boolean, onConnectButtonClicked: () -> Unit) {
    ConnectCardContent(
        isButtonEnabled = isButtonEnabled,
        onConnectButtonClicked = onConnectButtonClicked
    )
}

@Composable
fun ConnectCardContent(
    isButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
    onConnectButtonClicked: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                text = "1. Health & Body Information"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
                text = "Keep BodyStats updated with the latest information from your other apps, like your calories, heart rate and body measurements",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onConnectButtonClicked, enabled = isButtonEnabled) {
                Text(text = "Setup Health Connect")
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OverviewScreenPreviewDark() {
    BodyStatsTheme {
        ConnectCardContent(onConnectButtonClicked = {}, isButtonEnabled = true)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun OverviewScreenPreviewLight() {
    BodyStatsTheme {
        ConnectCardContent(onConnectButtonClicked = {}, isButtonEnabled = true)
    }
}