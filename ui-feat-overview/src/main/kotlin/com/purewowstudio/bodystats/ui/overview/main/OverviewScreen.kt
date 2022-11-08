package com.purewowstudio.bodystats.ui.overview.main

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.overview.actions.ActionsView
import com.purewowstudio.bodystats.ui.overview.healthcards.burnt.CaloriesBurntDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.calories.CaloriesDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.sleep.SleepDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.steps.StepsDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.weight.WeightDataCard

@Composable
fun OverviewScreen() {
    val context = LocalContext.current
    val viewModel = hiltViewModel<OverviewViewModel>()

    val permissionsLauncher = rememberLauncherForActivityResult(
        viewModel.getHealthDataPermissionContract()
    ) {
        viewModel.onPermissionsResult()
    }

    OverviewScreenContent(
        uiState = viewModel.uiState,
        onPermissionsButtonClicked = { permissionsLauncher.launch(viewModel.getHealthDataPermissions()) },
        onConnectButtonClicked = {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.healthdata")
                )
            )
        }
    )
}

@Composable
fun OverviewScreenContent(
    modifier: Modifier = Modifier,
    uiState: OverviewUiState,
    onPermissionsButtonClicked: () -> Unit,
    onConnectButtonClicked: () -> Unit,
) {
    Column(
        modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        if (uiState.showActionsView) {
            ActionsView(
                isConnectButtonEnabled = uiState.isConnectEnabled,
                isPermissionsButtonEnabled = uiState.isPermissionsEnabled,
                onConnectButtonClicked = onConnectButtonClicked,
                onPermissionsButtonClicked = onPermissionsButtonClicked
            )
        }

        if (uiState.showDataCards) {
            Text(
                text = "LAST 24 HOURS",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { SleepDataCard() }
                item { WeightDataCard() }
                item { CaloriesDataCard() }
                item { CaloriesBurntDataCard() }
                item { StepsDataCard() }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OverviewScreenPreviewDark() {
    BodyStatsTheme {
        OverviewScreenContent(
            uiState = OverviewUiState(
                showActionsView = true,
                showDataCards = true,
                isConnectEnabled = true,
                isPermissionsEnabled = true,
            ),
            onPermissionsButtonClicked = {},
            onConnectButtonClicked = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun OverviewScreenPreviewLight() {
    BodyStatsTheme {
        OverviewScreenContent(
            uiState = OverviewUiState(
                showActionsView = true,
                showDataCards = true,
                isConnectEnabled = true,
                isPermissionsEnabled = true,
            ),
            onPermissionsButtonClicked = {},
            onConnectButtonClicked = {}
        )
    }
}