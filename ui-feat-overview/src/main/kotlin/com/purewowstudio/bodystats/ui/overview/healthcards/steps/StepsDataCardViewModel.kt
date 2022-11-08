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
import javax.inject.Inject

@HiltViewModel
class StepsDataCardViewModel @Inject constructor(
    private val stepsDataCalories: HealthDataSteps
) : ViewModel() {

    var uiState by mutableStateOf<OverviewCardUiState>(OverviewCardUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            stepsDataCalories.readSteps()
                .onSuccess(::onStepsDataReturned)
                .onFailure(::onStepsDataFailureReturned)
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