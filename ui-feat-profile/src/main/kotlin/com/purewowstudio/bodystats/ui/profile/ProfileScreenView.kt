package com.purewowstudio.bodystats.ui.profile

import com.purewowstudio.bodystats.domain.entities.User

sealed class ProfileScreenView {
    object Loading: ProfileScreenView()
    data class Loaded(val user: User): ProfileScreenView()
}