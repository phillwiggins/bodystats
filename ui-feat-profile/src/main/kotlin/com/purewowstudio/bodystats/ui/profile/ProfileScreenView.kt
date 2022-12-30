package com.purewowstudio.bodystats.ui.profile

import com.purewowstudio.bodystats.domain.entities.User

sealed class ProfileScreenView {
    object Loading : ProfileScreenView()
    data class Loaded(
        val user: User,
        val isDOBDialogOpen: Boolean = false,
        val isGenderDialogOpen: Boolean = false,
        val isWeightDialogOpen: Boolean = false,
        val isHeightDialogOpen: Boolean = false,
    ) : ProfileScreenView()
}