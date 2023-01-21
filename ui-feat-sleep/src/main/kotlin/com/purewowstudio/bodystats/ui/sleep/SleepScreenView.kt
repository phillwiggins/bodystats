package com.purewowstudio.bodystats.ui.sleep

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

internal sealed class SleepScreenView {
    object Loading : SleepScreenView()

    @Immutable
    data class Loaded(
        val topRowItems: List<TopRowItem>,
        val startStopItems: List<StartStopItem>,
        val percentItems: List<PercentItem>
    ) : SleepScreenView()

    object Error : SleepScreenView()
}

internal data class TopRowItem(
    @StringRes val title: Int,
    val value: String,
    val icon: SleepIcon,
    val backgroundColor: SleepColors
)

internal data class StartStopItem(
    @StringRes val title: Int,
    val iconTint: SleepColors,
    val value: String,
    val icon: SleepIcon,
)

internal data class PercentItem(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    val value: Float,
    val icon: SleepIcon,
    val iconAndPercentColor: SleepColors,
    val duration: String
)

internal enum class SleepIcon {
    TOTAL_SLEEP, AWAKE_TIME, TIME_IN_BED, OUT_OF_BED, START_TIME, END_TIME, REM, LIGHT_SLEEP, DEEP_SLEEP
}

internal enum class SleepColors {
    TOTAL_SLEEP, AWAKE_TIME, OUT_OF_BED, START_TIME, END_TIME, REM, LIGHT_SLEEP, DEEP_SLEEP
}