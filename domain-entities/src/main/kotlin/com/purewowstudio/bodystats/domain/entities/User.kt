package com.purewowstudio.bodystats.domain.entities

import java.time.LocalDate

data class User(
    val name: String,
    val gender: Gender,
    val description: String,
    val avatarUrl: String,
    val dob: LocalDate,
)
