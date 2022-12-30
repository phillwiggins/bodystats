package com.purewowstudio.bodystats.domain.healthdata.models

import com.purewowstudio.bodystats.domain.entities.Weight

data class WeightRecord(
    val dateRecorded: String,
    val weight: Weight
)