package com.purewowstudio.bodystats.ui.overview.healthcards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.domain.base.toDayMonthYear
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.overview.healthcards.burnt.CaloriesBurntDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.calories.CaloriesDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.sleep.SleepDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.steps.StepsDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.total.TotalCard
import com.purewowstudio.bodystats.ui.overview.healthcards.weight.WeightDataCard
import java.time.LocalDate
import java.util.*

@Composable
fun DataCardsView(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<DataCardViewModel>()
    DataCardsViewContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onDateSelected = viewModel::onDateSelected
    )
}

@Composable
fun DataCardsViewContent(
    modifier: Modifier = Modifier,
    uiState: DataCardViewState,
    onDateSelected: (LocalDate) -> Unit,
) {
    DateCarousel(
        selectedDate = uiState.selectedDate,
        onDateSelected = { onDateSelected(it) })
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Text(
            text = " ${uiState.selectedDate.toDayMonthYear()}".toUpperCase(Locale.getDefault()),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = modifier.height(8.dp))
        TotalCard(date = uiState.selectedDate)
        Spacer(modifier = modifier.height(16.dp))
        Row {
            SleepDataCard(
                modifier = modifier.weight(0.5F, false),
                date = uiState.selectedDate
            )
            Spacer(modifier = modifier.width(8.dp))
            WeightDataCard(modifier = modifier.weight(0.5F, false))
        }
        Spacer(modifier = modifier.height(8.dp))
        Row {
            StepsDataCard(date = uiState.selectedDate)
        }
        Spacer(modifier = modifier.height(8.dp))
        Row {
            CaloriesDataCard(modifier = modifier.weight(0.5F, false))
            Spacer(modifier = modifier.width(8.dp))
            CaloriesBurntDataCard(modifier = modifier.weight(0.5F, false))
        }
    }
}

@Preview
@Composable
fun DataCardsViewPreview() {
    BodyStatsTheme {
        DataCardsViewContent(
            uiState = DataCardViewState(LocalDate.now()),
            onDateSelected = {
                // NO OP
            }
        )
    }
}
