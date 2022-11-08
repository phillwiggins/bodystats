package com.purewowstudio.bodystats.domain.healthdata

import com.purewowstudio.bodystats.domain.healthdata.models.CurrentWeight

interface HealthDataWeight {
    suspend fun readLatestWeight(): Result<CurrentWeight?>
}
