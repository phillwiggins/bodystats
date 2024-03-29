package com.purewowstudio.bodystats.ui.overview.healthcards.calories

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Kitchen
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.caloriesConsumed
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCard
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState
import java.time.LocalDate

@Composable
fun CaloriesDataCard(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onClicked: () -> Unit,
) {
    val viewModel = hiltViewModel<CaloriesDataCardViewModel>().apply {
        setInitialDate(date)
    }
    CaloriesDataCardContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onClicked = onClicked
    )
}

@Composable
fun CaloriesDataCardContent(
    modifier: Modifier = Modifier,
    uiState: OverviewCardUiState,
    onClicked: () -> Unit,
) {
    OverviewCard(
        modifier = modifier,
        title = "Kcal Eaten",
        state = uiState,
        backgroundColor = MaterialTheme.colorScheme.caloriesConsumed,
        icon = Icons.Outlined.Kitchen,
        onClicked = onClicked
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CaloriesDataCardPreviewDark() {
    BodyStatsTheme {
        CaloriesDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "1001",
                type = "kcals"
            ),
            onClicked = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CaloriesDataCardPreviewLight() {
    BodyStatsTheme {
        CaloriesDataCardContent(
            uiState = OverviewCardUiState.Loaded(
                amount = "1001",
                type = "kcals"
            ),
            onClicked = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CaloriesDataCardPreviewLoading() {
    BodyStatsTheme {
        CaloriesDataCardContent(
            uiState = OverviewCardUiState.Loading,
            onClicked = {}
        )
    }
}