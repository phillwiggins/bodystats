package com.purewowstudio.bodystats.ui.overview.healthcards.weight

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.weight
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCard
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState

@Composable
fun WeightDataCard(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
) {
    val viewModel = hiltViewModel<WeightDataCardViewModel>()
    WeightDataCardContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onClicked = onClicked
    )
}

@Composable
fun WeightDataCardContent(
    modifier: Modifier = Modifier,
    uiState: OverviewCardUiState,
    onClicked: () -> Unit,
) {
    OverviewCard(
        modifier = modifier,
        title = "Current Weight",
        state = uiState,
        backgroundColor = MaterialTheme.colorScheme.weight,
        icon = Icons.Outlined.MonitorWeight,
        onClicked = onClicked
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WeightDataCardContentPreviewDark() {
    BodyStatsTheme {
        WeightDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "93.05",
                type = "KG",
                subtitle = "Wed 05 May",
            ),
            onClicked = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeightDataCardContentPreviewLight() {
    BodyStatsTheme {
        WeightDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "93.05",
                type = "KG",
                subtitle = "Wed 05 May",
            ),
            onClicked = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WeightDataCardContentPreviewLoading() {
    BodyStatsTheme {
        WeightDataCardContent(
            uiState = OverviewCardUiState.Loading,
            onClicked = {}
        )
    }
}