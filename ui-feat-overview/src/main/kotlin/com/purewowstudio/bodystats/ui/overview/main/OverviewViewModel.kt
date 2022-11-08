package com.purewowstudio.bodystats.ui.overview.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.healthdata.HealthData
import com.purewowstudio.bodystats.domain.healthdata.models.HealthDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OverviewUiState(
    val isLoading: Boolean = true,
    val showDataCards: Boolean = false,
    val showActionsView: Boolean = false,
    val isConnectEnabled: Boolean = false,
    val isPermissionsEnabled: Boolean = false
)

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val healthData: HealthData
) : ViewModel() {

    var uiState by mutableStateOf(OverviewUiState())
        private set

    init {
        observeHealthData()
    }

    private fun observeHealthData() {
        healthData.state
            .onStart {
                healthData.checkAvailability()
                healthData.checkIfAllPermissionsAreGranted()
            }
            .onEach(::onHealthDataUpdated)
            .launchIn(viewModelScope)
    }

    private fun onHealthDataUpdated(it: HealthDataState) {
        val showActionsView = !it.isAvailable || !it.isPermissionsGranted
        val isConnectEnabled = !it.isAvailable
        val isPermissionsEnabled = it.isAvailable && !it.isPermissionsGranted
        val showDataCards = it.isAvailable && it.isPermissionsGranted

        uiState = uiState.copy(
            isLoading = false,
            showActionsView = showActionsView,
            isConnectEnabled = isConnectEnabled,
            isPermissionsEnabled = isPermissionsEnabled,
            showDataCards = showDataCards
        )
    }

    fun onPermissionsResult() {
        viewModelScope.launch { healthData.checkIfAllPermissionsAreGranted() }
    }

    fun getHealthDataPermissions() = healthData.permissions
    fun getHealthDataPermissionContract() = healthData.requestPermissionsActivityContract()
}

