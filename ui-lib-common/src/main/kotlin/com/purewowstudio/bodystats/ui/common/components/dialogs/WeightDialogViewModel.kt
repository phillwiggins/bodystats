package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.base.convertDecimalPartToInt
import com.purewowstudio.bodystats.domain.base.withSingleDecimalPlace
import com.purewowstudio.bodystats.domain.entities.Weight
import com.purewowstudio.bodystats.domain.stores.UserPrefsStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WeightDialogViewUiState(
    val isLoading: Boolean = true,
    val wholeNumbers: List<Int> = emptyList(),
    val decimals: List<Int> = emptyList(),
    val weightType: Weight.Type = Weight.Type.POUNDS,
    val selectedWholeNumber: Int = 0,
    val selectedDecimal: Int = 0,
)

@HiltViewModel
internal class WeightDialogViewModel @Inject constructor(
    private val userPrefs: UserPrefsStore
) : ViewModel() {

    val currentWeight = Weight.pounds(210.1)

    var uiState by mutableStateOf(WeightDialogViewUiState())
        private set

    init {
        viewModelScope.launch {
            val weightType = userPrefs.getWeightType()
            uiState = uiState.copy(
                isLoading = false,
                wholeNumbers = when (weightType) {
                    Weight.Type.KILOGRAMS -> buildIntList(bottom = MIN_KG, top = MAX_KG)
                    Weight.Type.POUNDS -> buildIntList(bottom = MIN_LBS, top = MAX_LBS)
                },
                decimals = getDecimalList(),
                weightType = weightType,
                selectedWholeNumber = getWholeNumberFromWeight(currentWeight, weightType),
                selectedDecimal = getDecimalNumberFromWeight(currentWeight, weightType)
            )
        }
    }

    fun onSelectedWholeNumberChanged(wholeNumber: Int) {
        uiState = uiState.copy(selectedWholeNumber = wholeNumber)
    }

    fun onSelectedDecimalNumberChanged(decimalNumber: Int) {
        uiState = uiState.copy(selectedDecimal = decimalNumber)
    }

    fun onWeightTypeChanged(weightType: Weight.Type) {
        val weightValueFromSelection =
            uiState.selectedWholeNumber + (uiState.selectedDecimal.toDouble() / 10)
        val weightFromSelection = when (uiState.weightType) {
            Weight.Type.KILOGRAMS -> Weight.kilograms(weightValueFromSelection)
            Weight.Type.POUNDS -> Weight.pounds(weightValueFromSelection)
        }
        val updatedWholeNumber = getWholeNumberFromWeight(weightFromSelection, weightType)
        val updatedDecimal = getDecimalNumberFromWeight(weightFromSelection, weightType)
        uiState = uiState.copy(
            wholeNumbers = when (weightType) {
                Weight.Type.KILOGRAMS -> buildIntList(top = MAX_KG, bottom = MIN_KG)
                Weight.Type.POUNDS -> buildIntList(top = MAX_LBS, bottom = MIN_LBS)
            },
            decimals = getDecimalList(),
            weightType = weightType,
            selectedWholeNumber = updatedWholeNumber,
            selectedDecimal = updatedDecimal
        )

        viewModelScope.launch {
            userPrefs.setWeightType(weightType = weightType)
        }
    }

    private fun getWholeNumberFromWeight(weight: Weight, weightType: Weight.Type): Int {
        return when (weightType) {
            Weight.Type.KILOGRAMS -> weight.inKilograms.toInt()
            Weight.Type.POUNDS -> weight.inPounds.toInt()
        }
    }

    private fun getDecimalNumberFromWeight(weight: Weight, weightType: Weight.Type): Int {
        val weightConverted = when (weightType) {
            Weight.Type.KILOGRAMS -> weight.inKilograms
            Weight.Type.POUNDS -> weight.inPounds
        }.withSingleDecimalPlace()
        return weightConverted.convertDecimalPartToInt()
    }

    private fun buildIntList(top: Int, bottom: Int): List<Int> {
        return buildList {
            val range = top - bottom
            add(bottom)
            repeat(range) {
                add(bottom + it)
            }
        }
    }

    private fun getDecimalList(): List<Int> {
        return buildList {
            repeat(10) {
                add(it)
            }
        }
    }

    companion object {
        private const val MAX_KG = 250
        private const val MIN_KG = 40
        private const val MAX_LBS = 500
        private const val MIN_LBS = 80
    }
}