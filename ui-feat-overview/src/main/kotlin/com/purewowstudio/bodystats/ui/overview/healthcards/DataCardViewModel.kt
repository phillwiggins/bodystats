package com.purewowstudio.bodystats.ui.overview.healthcards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

data class DataCardViewState(
    val selectedDate: LocalDate = LocalDate.now()
)

@HiltViewModel
internal class DataCardViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(DataCardViewState())
        private set

    fun onDateSelected(dateSelected: LocalDate) {
        uiState = uiState.copy(selectedDate = dateSelected)
    }

    companion object {
        private val LOG_TAG = DataCardViewModel::class.java.simpleName
    }
}