package com.purewowstudio.bodystats.ui.overview.healthcards.weight

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.healthdata.HealthDataWeight
import com.purewowstudio.bodystats.domain.healthdata.models.CurrentWeight
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState
import com.purewowstudio.bodystats.ui.overview.healthcards.sleep.SleepDataCardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightDataCardViewModel @Inject constructor(
    private val weightData: HealthDataWeight
) : ViewModel() {

    var uiState by mutableStateOf<OverviewCardUiState>(OverviewCardUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            weightData.readLatestWeight()
                .onSuccess(::onWeightDataReturned)
                .onFailure(::onWeightDataFailureReturned)
        }
    }

    private fun onWeightDataFailureReturned(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message ?: "Error")
        uiState = OverviewCardUiState.Error
    }

    private fun onWeightDataReturned(currentWeight: CurrentWeight?) {
        uiState = if (currentWeight == null) {
            OverviewCardUiState.NoData
        } else {
            OverviewCardUiState.Loaded(
                subtitle = currentWeight.dateRecorded,
                amount = currentWeight.amount,
                type = currentWeight.type
            )
        }
    }

    companion object {
        private val LOG_TAG = SleepDataCardViewModel::class.java.simpleName
    }
}