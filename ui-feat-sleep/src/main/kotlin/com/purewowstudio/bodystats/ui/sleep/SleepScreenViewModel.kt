package com.purewowstudio.bodystats.ui.sleep

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.base.getDateTimeAtEndOfDay
import com.purewowstudio.bodystats.domain.base.getDateTimeAtStartOfDay
import com.purewowstudio.bodystats.domain.base.toHoursAndMins
import com.purewowstudio.bodystats.domain.base.toSimpleTime
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSleep
import com.purewowstudio.bodystats.domain.healthdata.models.*
import com.purewowstudio.bodystats.ui.navigation.NavRoute
import com.purewowstudio.bodystats.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
internal class SleepScreenViewModel @Inject constructor(
    private val navManager: NavigationManager,
    private val sleepData: HealthDataSleep
) : ViewModel() {

    var uiState by mutableStateOf<SleepScreenView>(SleepScreenView.Loading)
        private set

    init {
        viewModelScope.launch {
            val date = LocalDate.now()
            sleepData.readSleepSessions(
                from = date.getDateTimeAtStartOfDay(),
                until = date.getDateTimeAtEndOfDay()
            )
                .onSuccess { setSuccessState(it) }
                .onFailure { uiState = SleepScreenView.Error }
        }
    }

    private fun setSuccessState(it: List<SleepSession>) {
        val sleepSession = it.first() // TODO - Remove when consuming averages
        uiState = SleepScreenView.Loaded(
            topRowItems = listOf(
                TopRowItem(
                    title = R.string.total_sleep,
                    value = sleepSession.duration?.toHoursAndMins() ?: "",
                    icon = SleepIcon.TOTAL_SLEEP,
                    backgroundColor = SleepColors.TOTAL_SLEEP
                ),
                TopRowItem(
                    title = R.string.time_in_bed,
                    value = sleepSession.getTimeInBed().toHoursAndMins(),
                    icon = SleepIcon.OUT_OF_BED,
                    backgroundColor = SleepColors.OUT_OF_BED
                ),
                TopRowItem(
                    title = R.string.awake_time,
                    value = sleepSession.getAwakeTime().toHoursAndMins(),
                    icon = SleepIcon.AWAKE_TIME,
                    backgroundColor = SleepColors.AWAKE_TIME
                ),
            ),
            startStopItems = listOf(
                StartStopItem(
                    title = R.string.start_time,
                    value = sleepSession.getTimeWentToBed().toString(),
                    icon = SleepIcon.START_TIME,
                    iconTint = SleepColors.START_TIME
                ),
                StartStopItem(
                    title = R.string.end_time,
                    value = LocalDateTime.ofInstant(
                        sleepSession.endTime,
                        sleepSession.endZoneOffset
                    ).toSimpleTime(),
                    icon = SleepIcon.END_TIME,
                    iconTint = SleepColors.END_TIME
                )
            ),
            percentItems = listOf(
                PercentItem(
                    title = R.string.rem,
                    subtitle = R.string.rem_aim,
                    value = getTimePercentage(sleepSession.duration, sleepSession.getREMTime()),
                    icon = SleepIcon.REM,
                    iconAndPercentColor = SleepColors.REM,
                    duration = sleepSession.getREMTime().toHoursAndMins()
                ),
                PercentItem(
                    title = R.string.light_sleep,
                    subtitle = R.string.light_sleep_aim,
                    value = getTimePercentage(sleepSession.duration, sleepSession.getLightTime()),
                    icon = SleepIcon.LIGHT_SLEEP,
                    iconAndPercentColor = SleepColors.LIGHT_SLEEP,
                    duration = sleepSession.getLightTime().toHoursAndMins()
                ),
                PercentItem(
                    title = R.string.deep_sleep,
                    subtitle = R.string.deep_sleep_aim,
                    value = getTimePercentage(sleepSession.duration, sleepSession.getDeepTime()),
                    icon = SleepIcon.DEEP_SLEEP,
                    iconAndPercentColor = SleepColors.DEEP_SLEEP,
                    duration = sleepSession.getDeepTime().toHoursAndMins()
                ),
                PercentItem(
                    title = R.string.out_of_bed,
                    subtitle = R.string.time_move,
                    value = getTimePercentage(sleepSession.duration, sleepSession.getOutOfBedTime()),
                    icon = SleepIcon.OUT_OF_BED,
                    iconAndPercentColor = SleepColors.OUT_OF_BED,
                    duration = sleepSession.getOutOfBedTime().toHoursAndMins()
                )
            )
        )
    }

    fun onBackPressed() {
        navManager.navigate(NavRoute.Back)
    }

    private fun getTimePercentage(totalDuration: Duration?, duration: Duration?): Float {
        if (totalDuration == null || duration == null) return 0F
        return ((duration.toMillis().toDouble() / totalDuration.toMillis().toDouble())).toFloat()
    }
}