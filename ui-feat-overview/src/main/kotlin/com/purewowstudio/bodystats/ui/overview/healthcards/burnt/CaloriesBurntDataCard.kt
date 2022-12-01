package com.purewowstudio.bodystats.ui.overview.healthcards.burnt

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.caloriesBurnt
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCard
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState
import java.time.LocalDate

@Composable
fun CaloriesBurntDataCard(modifier: Modifier = Modifier, date: LocalDate) {
    val viewModel = hiltViewModel<CaloriesBurntDataCardViewModel>().apply {
        setInitialDate(date)
    }
    CaloriesBurntDataCardContent(modifier = modifier, uiState = viewModel.uiState)
}

@Composable
fun CaloriesBurntDataCardContent(
    modifier: Modifier = Modifier,
    uiState: OverviewCardUiState
) {
    OverviewCard(
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