package com.purewowstudio.bodystats.domain.healthdata

import com.purewowstudio.bodystats.domain.healthdata.models.SleepSession
import java.time.LocalDateTime

interface HealthDataSleep {
    suspend fun readSleepSessions(
        from: LocalDateTime,
        until: LocalDateTime
    ): Result<List<SleepSession>>
}
