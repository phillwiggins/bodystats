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

@HiltViewModel
internal class OverviewViewModel @Inject constructor(
    private val healthData: HealthData
) : ViewModel() {

    var uiState by mutableStateOf<OverviewUiState>(OverviewUiState.Loading)
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

        uiState = if (showActionsView) {
            OverviewUiState.ActionsRequired(
                isConnectEnabled = isConnectEnabled,
                isPermissionsEnabled = isPermissionsEnabled
            )
        } else {
            OverviewUiState.DataCards
        }

    }

    fun onPermissionsResult() {
        viewModelScope.launch { healthData.checkIfAllPermissionsAreGranted() }
    }

    fun getHealthDataPermissions() = healthData.permissions
    fun getHealthDataPermissionContract() = healthData.requestPermissionsActivityContract()
}

