package com.purewowstudio.bodystats.domain.healthdata

import com.purewowstudio.bodystats.domain.healthdata.models.SleepSession

interface HealthDataSleep {
    suspend fun readSleepSessions(days: Int = 1): Result<List<SleepSession>>
}
