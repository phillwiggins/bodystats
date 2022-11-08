package com.purewowstudio.bodystats.ui.overview.healthcards.sleep

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KingBed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.sleep
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCard
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState

@Composable
fun SleepDataCard() {
    val viewModel = hiltViewModel<SleepDataCardViewModel>()
    SleepDataCardContent(uiState = viewModel.uiState)
}

@Composable
fun SleepDataCardContent(
    modifier: Modifier = Modifier,
    uiState: OverviewCardUiState
) {
    OverviewCard(
        modifier = modifier,
        title = "Sleep",
        state = uiState,
        backgroundColor = MaterialTheme.colorScheme.sleep,
        icon = Icons.Outlined.KingBed
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OverviewScreenPreviewDark() {
    BodyStatsTheme {
        SleepDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "8h, 30m",
                subtitle = "9:35-8:45",
                type = "test"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun OverviewScreenPreviewLight() {
    BodyStatsTheme {
        SleepDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "8h, 30m",
                subtitle = "9:35-8:45",
                type = "test",
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun OverviewScreenPreviewLoading() {
    BodyStatsTheme {
        SleepDataCardContent(
            uiState = OverviewCardUiState.Loading
        )
    }
}