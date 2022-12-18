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
import java.time.LocalDate

@Composable
fun SleepDataCard(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onClicked: () -> Unit
) {
    val viewModel = hiltViewModel<SleepDataCardViewModel>().apply { setInitialDate(date) }
    SleepDataCardContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onClicked = onClicked
    )
}

@Composable
fun SleepDataCardContent(
    modifier: Modifier = Modifier,
    uiState: OverviewCardUiState,
    onClicked: () -> Unit,
) {
    OverviewCard(
        modifier = modifier,
        title = "Sleep",
        state = uiState,
        backgroundColor = MaterialTheme.colorScheme.sleep,
        icon = Icons.Outlined.KingBed,
        onClicked = onClicked
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
            ),
            onClicked = {}
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
            ),
            onClicked = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun OverviewScreenPreviewLoading() {
    BodyStatsTheme {
        SleepDataCardContent(
            uiState = OverviewCardUiState.Loading,
            onClicked = {}
        )
    }
}