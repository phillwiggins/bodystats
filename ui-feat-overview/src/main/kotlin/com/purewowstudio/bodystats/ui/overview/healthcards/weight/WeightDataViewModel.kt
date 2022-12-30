package com.purewowstudio.bodystats.ui.overview.healthcards.weight

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.base.withTwoDecimalPlaces
import com.purewowstudio.bodystats.domain.entities.Weight
import com.purewowstudio.bodystats.domain.healthdata.HealthDataWeight
import com.purewowstudio.bodystats.domain.healthdata.models.WeightRecord
import com.purewowstudio.bodystats.domain.stores.UserPrefsStore
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState
import com.purewowstudio.bodystats.ui.overview.healthcards.sleep.SleepDataCardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightDataCardViewModel @Inject constructor(
    private val weightData: HealthDataWeight,
    private val userPrefsStore: UserPrefsStore
) : ViewModel() {

    var uiState by mutableStateOf<OverviewCardUiState>(OverviewCardUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            val weightPref = userPrefsStore.getWeightType()
            weightData.readLatestWeight()
                .onSuccess { onWeightDataReturned(it, weightPref) }
                .onFailure(::onWeightDataFailureReturned)
        }
    }

    private fun onWeightDataFailureReturned(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message ?: "Error")
        uiState = OverviewCardUiState.Error
    }

    private fun onWeightDataReturned(weightRecord: WeightRecord?, weightPref: Weight.Type) {
        uiState = if (weightRecord == null) {
            OverviewCardUiState.NoData
        } else {
            OverviewCardUiState.Loaded(
                subtitle = weightRecord.dateRecorded,
                amount = when (weightPref) {
                    Weight.Type.KILOGRAMS -> weightRecord.weight.inKilograms
                    Weight.Type.POUNDS -> weightRecord.weight.inPounds
                }.withTwoDecimalPlaces().toString(),
                type = when (weightPref) {
                    Weight.Type.KILOGRAMS -> "Kg"
                    Weight.Type.POUNDS -> "Lb"
                }
            )
        }
    }

    companion object {
        private val LOG_TAG = SleepDataCardViewModel::class.java.simpleName
    }
}