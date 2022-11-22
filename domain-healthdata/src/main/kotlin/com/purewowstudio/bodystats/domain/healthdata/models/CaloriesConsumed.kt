package com.purewowstudio.bodystats.domain.healthdata.models

data class CaloriesConsumed(
    val burnt: Int,
    val consumed: Int,
    val proteinGram: Int,
    val carbGram: Int,
    val fatGrm: Int
)