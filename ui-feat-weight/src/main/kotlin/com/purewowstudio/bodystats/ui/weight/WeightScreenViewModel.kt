package com.purewowstudio.bodystats.ui.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.healthdata.HealthDataWeight
import com.purewowstudio.bodystats.ui.navigation.NavRoute
import com.purewowstudio.bodystats.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
internal class WeightScreenViewModel @Inject constructor(
    private val navManager: NavigationManager,
    private val weightData: HealthDataWeight
) : ViewModel() {

    var uiState by mutableStateOf<WeightScreenView>(WeightScreenView.Loading)
        private set

    init {
        viewModelScope.launch {
            val date = LocalDate.now()
        }
    }

    private fun setSuccessState() {

    }

    fun onBackPressed() {
        navManager.navigate(NavRoute.Back)
    }
}