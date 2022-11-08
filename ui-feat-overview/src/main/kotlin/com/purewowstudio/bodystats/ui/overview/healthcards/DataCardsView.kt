package com.purewowstudio.bodystats.ui.overview.healthcards

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.overview.healthcards.burnt.CaloriesBurntDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.calories.CaloriesDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.sleep.SleepDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.steps.StepsDataCard
import com.purewowstudio.bodystats.ui.overview.healthcards.total.TotalCard
import com.purewowstudio.bodystats.ui.overview.healthcards.weight.WeightDataCard

@Composable
fun DataCardsView(
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "LAST 24 HOURS - KCALS",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelSmall
        )
        TotalCard()
        Spacer(modifier = modifier.height(16.dp))
        Text(
            text = "LAST 24 HOURS - STATS",
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