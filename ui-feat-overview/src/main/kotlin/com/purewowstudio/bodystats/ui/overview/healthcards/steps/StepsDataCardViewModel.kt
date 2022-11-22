package com.purewowstudio.bodystats.ui.overview.healthcards.steps

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSteps
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class StepsDataCardViewModel @Inject constructor(
    private val stepsDataCalories: HealthDataSteps
) : ViewModel() {

    var uiState by mutableStateOf<OverviewCardUiState>(OverviewCardUiState.Loading)
        private set


    fun setInitialDate(date: LocalDate) {
        viewModelScope.launch {
            stepsDataCalories.readSteps(
                until = LocalDateTime.now()
                    .withYear(date.year)
                    .withDayOfYear(date.dayOfYear)
                    .withMinute(59)
                    .withHour(23),
                from = LocalDateTime.now()
                    .withYear(date.year)
                    .withDayOfYear(date.dayOfYear)
                    .withMinute(0)
                    .withHour(0)
            )
                .onSuccess { onStepsDataReturned(it) }
                .onFailure { onStepsDataFailureReturned(it) }
        }
    }

    private fun onStepsDataFailureReturned(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message ?: "Error")
        uiState = OverviewCardUiState.Error
    }

    private fun onStepsDataReturned(steps: Int?) {
        uiState = if (steps == null) {
            OverviewCardUiState.NoData
        } else {
            OverviewCardUiState.Loaded(
                amount = "$steps",
                type = "steps"
            )
        }
    }

    companion object {
        private val LOG_TAG = StepsDataCardViewModel::class.java.simpleName
    }
}