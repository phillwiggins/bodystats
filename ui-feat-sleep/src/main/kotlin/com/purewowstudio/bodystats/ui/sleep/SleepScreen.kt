package com.purewowstudio.bodystats.ui.sleep

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.components.ColourCard
import com.purewowstudio.bodystats.ui.common.components.ErrorScreenContent
import com.purewowstudio.bodystats.ui.common.theme.*

@Composable
fun SleepScreen() {
    val viewModel = hiltViewModel<SleepScreenViewModel>()
    SleepScreenContent(
        uiState = viewModel.uiState,
    )
    BackHandler(enabled = true, onBack = viewModel::onBackPressed)
}

@Composable
private fun SleepScreenContent(
    uiState: SleepScreenView,
) {
    when (uiState) {
        SleepScreenView.Error -> ErrorScreenContent()
        SleepScreenView.Loading -> SleepScreenLoadingView()
        is SleepScreenView.Loaded -> SleepScreenLoadedView(uiState = uiState)
    }
}

@Composable
fun SleepScreenLoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SleepScreenLoadedView(
    modifier: Modifier = Modifier,
    uiState: SleepScreenView.Loaded
) {
    Column(
        modifier = modifier.padding(all = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sleep_duration),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(12.dp))
        TopRow(items = uiState.topRowItems)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.sleep_stages),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(12.dp))
        StartStopRow(items = uiState.startStopItems)
        Spacer(modifier = Modifier.height(12.dp))
        Body(items = uiState.percentItems)
    }
}

@Composable
private fun TopRow(items: List<TopRowItem>) {
    Row {
        items.forEachIndexed { index, topRowItem ->
            if (index != 0) Spacer(modifier = Modifier.width(12.dp))
            TopRowView(
                modifier = Modifier
                    .weight(0.3F, false)
                    .height(120.dp),
                topRowItem = topRowItem
            )
        }
    }
}

@Composable
private fun TopRowView(modifier: Modifier, topRowItem: TopRowItem) {
    ColourCard(
        modifier = modifier,
        backgroundColor = getSleepColor(sleepColors = topRowItem.backgroundColor),
        icon = getSleepIcon(sleepIcon = topRowItem.icon)
    ) {
        Text(
            color = MaterialTheme.colorScheme.colorOnCustom,
            style = MaterialTheme.typography.titleSmall,
            text = stringResource(id = topRowItem.title)
        )
        Text(
            color = MaterialTheme.colorScheme.colorOnCustom,
            style = MaterialTheme.typography.titleMedium,
            text = topRowItem.value
        )
    }
}

@Composable
private fun StartStopRow(items: List<StartStopItem>) {
    Row {
        items.forEachIndexed { index, topRowItem ->
            if (index != 0) Spacer(modifier = Modifier.width(16.dp))
            StartStopView(
                modifier = Modifier
                    .weight(1F)
                    .height(82.dp),
                topRowItem = topRowItem
            )
        }
    }
}

@Composable
private fun StartStopView(modifier: Modifier, topRowItem: StartStopItem) {
    OutlinedCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .height(44.dp)
                    .width(44.dp)
                    .weight(0.8F),
                imageVector = getSleepIcon(sleepIcon = topRowItem.icon),
                tint = getSleepColor(sleepColors = topRowItem.iconTint),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .weight(1.2F)
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                    text = stringResource(id = topRowItem.title)
                )
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                    text = topRowItem.value
                )
            }
        }
    }
}

@Composable
private fun Body(items: List<PercentItem>) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
        ) {
            items.forEachIndexed { index, percentItem ->
                BodyRow(
                    modifier = Modifier.padding(bottom = if (items.lastIndex == index) 0.dp else 16.dp),
                    percentItem = percentItem
                )
            }
        }
    }
}

@Composable
private fun BodyRow(
    modifier: Modifier = Modifier,
    percentItem: PercentItem
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(weight = 1.5F),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .height(34.dp)
                    .width(34.dp),
                imageVector = getSleepIcon(sleepIcon = percentItem.icon),
                tint = getSleepColor(sleepColors = percentItem.iconAndPercentColor),
                contentDescription = ""
            )
        }
        Column(modifier = Modifier
            .weight(weight = 6F)
            .padding(start = 2.dp, end = 2.dp)) {
            Text(
                text = stringResource(id = percentItem.title),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = percentItem.value,
                color = getSleepColor(sleepColors = percentItem.iconAndPercentColor),
                modifier = Modifier
                    .height(5.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = percentItem.subtitle),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Column(
            modifier = Modifier.weight(weight = 2F),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = percentItem.duration,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun getSleepColor(sleepColors: SleepColors): Color {
    return when (sleepColors) {
        SleepColors.TOTAL_SLEEP -> MaterialTheme.colorScheme.weight
        SleepColors.AWAKE_TIME -> MaterialTheme.colorScheme.sleep
        SleepColors.OUT_OF_BED -> MaterialTheme.colorScheme.caloriesBurnt
        SleepColors.START_TIME -> MaterialTheme.colorScheme.constructive
        SleepColors.END_TIME -> MaterialTheme.colorScheme.destructive
        SleepColors.REM -> MaterialTheme.colorScheme.weight
        SleepColors.LIGHT_SLEEP -> MaterialTheme.colorScheme.steps
        SleepColors.DEEP_SLEEP -> MaterialTheme.colorScheme.primary
    }
}

@Composable
private fun getSleepIcon(sleepIcon: SleepIcon): ImageVector {
    return when (sleepIcon) {
        SleepIcon.TOTAL_SLEEP -> ImageVector.vectorResource(id = R.drawable.icon_sleep)
        SleepIcon.AWAKE_TIME -> ImageVector.vectorResource(id = R.drawable.icon_awake)
        SleepIcon.OUT_OF_BED -> ImageVector.vectorResource(id = R.drawable.icon_out_of_bed)
        SleepIcon.START_TIME -> ImageVector.vectorResource(id = R.drawable.icon_start_time)
        SleepIcon.END_TIME -> ImageVector.vectorResource(id = R.drawable.icon_end_time)
        SleepIcon.REM -> ImageVector.vectorResource(id = R.drawable.icon_rem)
        SleepIcon.LIGHT_SLEEP -> ImageVector.vectorResource(id = R.drawable.icon_light)
        SleepIcon.DEEP_SLEEP -> ImageVector.vectorResource(id = R.drawable.icon_deep)
        SleepIcon.TIME_IN_BED -> ImageVector.vectorResource(id = R.drawable.icon_deep)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPreview() {
    BodyStatsTheme {
        SleepScreenLoadedView(
            uiState = fakeLoadedData,
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun TopRowViewPreview() {
    BodyStatsTheme {
        TopRowView(
            modifier = Modifier
                .width(180.dp)
                .height(120.dp),
            topRowItem = TopRowItem(
                title = R.string.out_of_bed,
                value = "8hrs",
                icon = SleepIcon.OUT_OF_BED,
                backgroundColor = SleepColors.OUT_OF_BED
            )
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun StartEndViewPreview() {
    BodyStatsTheme {
        StartStopView(
            modifier = Modifier
                .width(180.dp)
                .height(120.dp),
            topRowItem = StartStopItem(
                title = R.string.start_time,
                value = "8hrs",
                icon = SleepIcon.START_TIME,
                iconTint = SleepColors.START_TIME
            ),
        )
    }
}

private val fakeLoadedData = SleepScreenView.Loaded(
    topRowItems = listOf(
        TopRowItem(
            title = R.string.total_sleep,
            value = "8hrs",
            icon = SleepIcon.TOTAL_SLEEP,
            backgroundColor = SleepColors.TOTAL_SLEEP
        ),
        TopRowItem(
            title = R.string.awake_time,
            value = "8hrs",
            icon = SleepIcon.AWAKE_TIME,
            backgroundColor = SleepColors.AWAKE_TIME
        ),
        TopRowItem(
            title = R.string.out_of_bed,
            value = "8hrs",
            icon = SleepIcon.OUT_OF_BED,
            backgroundColor = SleepColors.OUT_OF_BED
        )
    ),
    startStopItems = listOf(
        StartStopItem(
            title = R.string.start_time,
            value = "8hrs",
            icon = SleepIcon.START_TIME,
            iconTint = SleepColors.START_TIME
        ),
        StartStopItem(
            title = R.string.end_time,
            value = "8hrs",
            icon = SleepIcon.END_TIME,
            iconTint = SleepColors.END_TIME
        )
    ),
    percentItems = listOf(
        PercentItem(
            title = R.string.rem,
            subtitle = R.string.rem_aim,
            value = 0.33F,
            icon = SleepIcon.REM,
            iconAndPercentColor = SleepColors.REM,
            duration = "8hrs"
        ),
        PercentItem(
            title = R.string.light_sleep,
            subtitle = R.string.light_sleep_aim,
            value = 0.33F,
            icon = SleepIcon.LIGHT_SLEEP,
            iconAndPercentColor = SleepColors.LIGHT_SLEEP,
            duration = "8hrs"
        ),
        PercentItem(
            title = R.string.deep_sleep,
            subtitle = R.string.deep_sleep_aim,
            value = 0.33F,
            icon = SleepIcon.DEEP_SLEEP,
            iconAndPercentColor = SleepColors.DEEP_SLEEP,
            duration = "8hrs"
        )
    )
)