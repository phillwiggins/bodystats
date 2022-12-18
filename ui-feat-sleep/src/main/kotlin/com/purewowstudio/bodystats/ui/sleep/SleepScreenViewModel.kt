package com.purewowstudio.bodystats.ui.sleep

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.base.getDateTimeAtEndOfDay
import com.purewowstudio.bodystats.domain.base.getDateTimeAtStartOfDay
import com.purewowstudio.bodystats.domain.base.toFormattedDuration
import com.purewowstudio.bodystats.domain.base.toSimpleTime
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSleep
import com.purewowstudio.bodystats.domain.healthdata.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
internal class SleepScreenViewModel @Inject constructor(
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
            startTime = LocalDateTime.ofInstant(
                sleepSession.startTime,
                sleepSession.startZoneOffset
            ).toSimpleTime(),
            endTime = LocalDateTime.ofInstant(
                sleepSession.endTime,
                sleepSession.endZoneOffset
            ).toSimpleTime(),
            duration = sleepSession.duration?.toFormattedDuration() ?: "",
            awakeTime = sleepSession.getAwakeTime().toFormattedDuration(),
            lightTime = sleepSession.getLightTime().toFormattedDuration(),
            remTime = sleepSession.getREMTime().toFormattedDuration(),
            deepTime = sleepSession.getDeepTime().toFormattedDuration(),
            outOfBedTime = sleepSession.getOutOfBedTime().toFormattedDuration()
        )
    }
}