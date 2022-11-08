package com.purewowstudio.bodystats.ui.overview.healthcards.burnt

import android.content.res.Configuration
import android.provider.CalendarContract
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.color.MaterialColors
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.caloriesBurnt
import com.purewowstudio.bodystats.ui.overview.healthcards.BaseOverviewCard
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState

@Composable
fun CaloriesBurntDataCard() {
    val viewModel = hiltViewModel<CaloriesBurntDataCardViewModel>()
    CaloriesBurntDataCardContent(uiState = viewModel.uiState)
}

@Composable
fun CaloriesBurntDataCardContent(
    modifier: Modifier = Modifier,
    uiState: OverviewCardUiState
) {
    BaseOverviewCard(
        modifier = modifier,
        title = "Kcal Burnt",
        state = uiState,
        backgroundColor = MaterialTheme.colorScheme.caloriesBurnt,
        icon = Icons.Outlined.LocalFireDepartment
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CaloriesBurntDataCardPreviewDark() {
    BodyStatsTheme {
        CaloriesBurntDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "1000",
                type = "kcals"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CaloriesBurntDataCardPreviewLight() {
    BodyStatsTheme {
        CaloriesBurntDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "1000",
                type = "kcals"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CaloriesBurntDataCardPreviewLoading() {
    BodyStatsTheme {
        CaloriesBurntDataCardContent(
            uiState = OverviewCardUiState.Loading
        )
    }
}