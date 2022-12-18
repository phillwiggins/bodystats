package com.purewowstudio.bodystats.ui.sleep

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Total Duration")
        Text(uiState.duration)
        Text("Start time")
        Text(uiState.startTime)
        Text("End time")
        Text(uiState.endTime)
        Text("Time awake")
        Text(uiState.awakeTime)
        Text("Light sleep")
        Text(uiState.lightTime)
        Text("REM")
        Text(uiState.remTime)
        Text("Deep sleep")
        Text(uiState.deepTime)
        Text("Out of bed")
        Text(uiState.outOfBedTime)
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