package com.purewowstudio.bodystats.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purewowstudio.bodystats.domain.entities.Gender
import com.purewowstudio.bodystats.domain.stores.UserStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
internal class ProfileScreenViewModel @Inject constructor(
    private val userStore: UserStore
) : ViewModel() {

    var uiState by mutableStateOf<ProfileScreenView>(ProfileScreenView.Loading)
        private set

    init {
        observeUserStore()
    }

    private fun observeUserStore() {
        userStore.getUserDetailsStream().onEach {
            uiState = ProfileScreenView.Loaded(user = it)
        }.launchIn(viewModelScope)
    }

    fun onDOBUpdated(dob: LocalDate) = viewModelScope.launch {
        val updatedUser = userStore.getUserDetailsStream().first().copy(dob = dob)
        userStore.setUser(user = updatedUser)
    }

    fun onGenderUpdated(gender: Gender) = viewModelScope.launch {
        val updatedUser = userStore.getUserDetailsStream().first().copy(gender = gender)
        userStore.setUser(user = updatedUser)
    }
}