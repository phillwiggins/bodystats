package com.purewowstudio.bodystats.ui.overview.healthcards.steps

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsWalk
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.steps
import com.purewowstudio.bodystats.ui.overview.healthcards.BaseOverviewCard
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState

@Composable
fun StepsDataCard() {
    val viewModel = hiltViewModel<StepsDataCardViewModel>()
    StepsDataCardContent(uiState = viewModel.uiState)
}

@Composable
fun StepsDataCardContent(
    modifier: Modifier = Modifier,
    uiState: OverviewCardUiState
) {
    BaseOverviewCard(
        modifier = modifier,
        title = "Steps",
        state = uiState,
        backgroundColor = MaterialTheme.colorScheme.steps,
        icon = Icons.Outlined.DirectionsWalk
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CaloriesBurntDataCardPreviewDark() {
    BodyStatsTheme {
        StepsDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "9650",
                type = "steps"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CaloriesBurntDataCardPreviewLight() {
    BodyStatsTheme {
        StepsDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "9650",
                type = "steps"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CaloriesBurntDataCardPreviewLoading() {
    BodyStatsTheme {
        StepsDataCardContent(
            uiState = OverviewCardUiState.Loading
        )
    }
}