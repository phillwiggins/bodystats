package com.purewowstudio.bodystats.ui.sleep

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.domain.base.toSimpleTime
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import java.time.LocalDateTime
import java.time.Month

@Composable
fun SleepScreen() {
    val viewModel = hiltViewModel<SleepScreenViewModel>()
    SleepScreenContent(
        uiState = viewModel.uiState,
    )

    BackHandler(enabled = true, onBack = viewModel::onBackPressed)
}

@Composable
fun SleepScreenContent(
    uiState: SleepScreenView,
) {
    when (uiState) {
        SleepScreenView.Error -> SleepScreenErrorView()
        SleepScreenView.Loading -> SleepScreenLoadingView()
        is SleepScreenView.Loaded -> SleepScreenLoadedView(uiState = uiState)
    }
}

@Composable
fun SleepScreenErrorView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ERROR")
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
fun SleepScreenLoadedView(
    modifier: Modifier = Modifier,
    uiState: SleepScreenView.Loaded
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            "Durations",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        IconWithTitleAndDescription(
            icon = Icons.Outlined.Hotel,
            title = "Total",
            description = "Aim for between 6-9 hours",
            value = uiState.duration
        )
        IconWithTitleAndDescription(
            icon = Icons.Outlined.NightsStay,
            title = "Start time",
            description = "Time fell to sleep",
            value = uiState.startTime
        )
        IconWithTitleAndDescription(
            icon = Icons.Outlined.Alarm,
            title = "End time",
            description = "Time you woke up",
            value = uiState.endTime
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Stages",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        IconWithTitleAndDescription(
            icon = Icons.Outlined.Accessibility,
            title = "Time awake",
            description = "Aim for between 5-20%",
            value = uiState.awakeTime
        )
        IconWithTitleAndDescription(
            icon = Icons.Outlined.Psychology,
            title = "REM",
            description = "Aim for between 15-25%",
            value = uiState.remTime
        )
        IconWithTitleAndDescription(
            icon = Icons.Outlined.ModelTraining,
            title = "Light",
            description = "Aim for between 40-60%",
            value = uiState.lightTime
        )
        IconWithTitleAndDescription(
            icon = Icons.Outlined.OfflineBolt,
            title = "Deep",
            description = "Aim for between 15-25%",
            value = uiState.deepTime
        )
        IconWithTitleAndDescription(
            icon = Icons.Outlined.DirectionsWalk,
            title = "Time out of bed",
            description = "Try and reduce this for a better sleep",
            value = uiState.outOfBedTime
        )
    }
}

@Composable
private fun IconWithTitleAndDescription(
    icon: ImageVector,
    title: String,
    description: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPreviewLight() {
    BodyStatsTheme {
        SleepScreenLoadedView(
            uiState = fakeLoadedData
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreviewDark() {
    BodyStatsTheme {
        SleepScreenLoadedView(
            uiState = fakeLoadedData,
        )
    }
}

private val fakeLoadedData = SleepScreenView.Loaded(
    startTime = LocalDateTime.of(
        2022,
        Month.MAY,
        1,
        22,
        0
    ).toSimpleTime(),
    endTime = LocalDateTime.of(
        2022,
        Month.MAY,
        2,
        6,
        0
    ).toSimpleTime(),
    duration = "8hrs",
    awakeTime = "25 mins",
    lightTime = "1hr 40 mins",
    remTime = "3hr 20 mins",
    deepTime = "3hr 35 mins",
    outOfBedTime = "2 mins",
)