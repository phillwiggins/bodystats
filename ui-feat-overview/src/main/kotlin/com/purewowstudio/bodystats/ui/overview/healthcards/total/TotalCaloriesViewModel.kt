package com.purewowstudio.bodystats.ui.overview.healthcards.total

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.base.getDateTimeAtEndOfDay
import com.purewowstudio.bodystats.domain.base.getDateTimeAtStartOfDay
import com.purewowstudio.bodystats.domain.healthdata.HealthDataCalories
import com.purewowstudio.bodystats.domain.healthdata.models.CaloriesConsumed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
internal class TotalCaloriesViewModel @Inject constructor(
    private val caloriesDataCalories: HealthDataCalories
) : ViewModel() {

    var uiState by mutableStateOf(TotalCaloriesView())
        private set

    fun setInitialDate(date: LocalDate) {
        viewModelScope.launch {
            caloriesDataCalories
                .readConsumed(
                    until = date.getDateTimeAtEndOfDay(),
                    from = date.getDateTimeAtStartOfDay()
                )
                .onSuccess { onCaloriesConsumedReturned(it) }
                .onFailure { onCaloriesConsumedFailureReturned(it) }
        }
    }

    private fun onCaloriesConsumedReturned(calories: CaloriesConsumed) {
        uiState = uiState.copy(
            caloriesBurnt = calories.burnt,
            caloriesConsumed = calories.consumed,
            proteinGrams = calories.proteinGram,
            fatGrams = calories.fatGrm,
            carbsGrams = calories.carbGram
        )
    }

    private fun onCaloriesConsumedFailureReturned(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message ?: "Error")
    }

    companion object {
        private val LOG_TAG = TotalCaloriesViewModel::class.java.simpleName
    }
}