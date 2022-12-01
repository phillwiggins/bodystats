package com.purewowstudio.bodystats.ui.overview.healthcards.burnt

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.base.getDateTimeAtEndOfDay
import com.purewowstudio.bodystats.domain.base.getDateTimeAtStartOfDay
import com.purewowstudio.bodystats.domain.healthdata.HealthDataCalories
import com.purewowstudio.bodystats.ui.overview.healthcards.OverviewCardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CaloriesBurntDataCardViewModel @Inject constructor(
    private val caloriesDataCalories: HealthDataCalories
) : ViewModel() {

    var uiState by mutableStateOf<OverviewCardUiState>(OverviewCardUiState.Loading)
        private set

    fun setInitialDate(date: LocalDate) {
        viewModelScope.launch {
            caloriesDataCalories
                .readBurnt(
                    from = date.getDateTimeAtStartOfDay(),
                    until = date.getDateTimeAtEndOfDay(),
                )
                .onSuccess(::onCaloriesDataReturned)
                .onFailure(::onCaloriesDataFailureReturned)
        }
    }

    private fun onCaloriesDataFailureReturned(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message ?: "Error")
        uiState = OverviewCardUiState.Error
    }

    private fun onCaloriesDataReturned(calories: Int?) {
        uiState = if (calories == null) {
            OverviewCardUiState.NoData
        } else {
            OverviewCardUiState.Loaded(
                amount = "$calories",
                type = "kcals"
            )
        }
    }

    companion object {
        private val LOG_TAG = CaloriesBurntDataCardViewModel::class.java.simpleName
    }
}