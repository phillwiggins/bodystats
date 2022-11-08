package com.purewowstudio.bodystats.ui.overview.main

internal sealed class OverviewUiState {
    object Loading : OverviewUiState()
    data class ActionsRequired(
        val isConnectEnabled: Boolean = false,
        val isPermissionsEnabled: Boolean = false
    ) : OverviewUiState()

    object DataCards : OverviewUiState()
}