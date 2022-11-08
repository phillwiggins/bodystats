package com.purewowstudio.bodystats.domain.healthdata.models

data class CurrentWeight(
    val dateRecorded: String,
    val amount: String,
    val type: String,
)