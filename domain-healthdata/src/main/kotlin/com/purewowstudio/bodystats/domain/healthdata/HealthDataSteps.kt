package com.purewowstudio.bodystats.domain.healthdata

import java.time.LocalDateTime

interface HealthDataSteps {
    suspend fun readSteps(
        from: LocalDateTime = LocalDateTime.now()
            .withHour(0)
            .withMinute(0)
            .withSecond(0),
        until: LocalDateTime = LocalDateTime.now()
    ): Result<Int?>
}
