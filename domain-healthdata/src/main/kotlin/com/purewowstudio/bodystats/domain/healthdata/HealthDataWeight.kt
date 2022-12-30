package com.purewowstudio.bodystats.domain.healthdata

import com.purewowstudio.bodystats.domain.healthdata.models.WeightRecord

interface HealthDataWeight {
    suspend fun readLatestWeight(): Result<WeightRecord?>
}
