package com.purewowstudio.bodystats.ui.overview.actions

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.overview.actions.connect.ConnectCard
import com.purewowstudio.bodystats.ui.overview.actions.permissions.RequestPermissionsCard

@Composable
fun ActionsView(
    modifier: Modifier = Modifier,
    isConnectButtonEnabled: Boolean,
    isPermissionsButtonEnabled: Boolean,
    onConnectButtonClicked: () -> Unit,
    onPermissionsButtonClicked: () -> Unit,
) {
    Column {
        Text(
            text = "ACTIONS REQUIRED",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = modifier.height(8.dp))
        ConnectCard(
            isButtonEnabled = isConnectButtonEnabled,
            onConnectButtonClicked = onConnectButtonClicked
        )
        Spacer(modifier = modifier.height(8.dp))
        RequestPermissionsCard(
            isButtonEnabled = isPermissionsButtonEnabled,
            onPermissionsClicked = onPermissionsButtonClicked
        )
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ActionsViewPreviewDark() {
    BodyStatsTheme {
        ActionsView(
            isConnectButtonEnabled = true,
            isPermissionsButtonEnabled = true,
            onPermissionsButtonClicked = {},
            onConnectButtonClicked = {}
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun ActionsViewPreviewLight() {
    BodyStatsTheme {
        ActionsView(
            isConnectButtonEnabled = true,
            isPermissionsButtonEnabled = true,
            onPermissionsButtonClicked = {},
            onConnectButtonClicked = {}
        )
    }
}