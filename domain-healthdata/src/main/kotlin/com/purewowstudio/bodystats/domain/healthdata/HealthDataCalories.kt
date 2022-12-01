package com.purewowstudio.bodystats.domain.healthdata

import com.purewowstudio.bodystats.domain.healthdata.models.CaloriesConsumed
import java.time.LocalDateTime

interface HealthDataCalories {
    suspend fun readConsumed(
        from: LocalDateTime = LocalDateTime.now()
            .withHour(0)
            .withMinute(0)
            .withSecond(0),
        until: LocalDateTime = LocalDateTime.now()
    ): Result<CaloriesConsumed>

    suspend fun readBurnt(
        from: LocalDateTime = LocalDateTime.now()
            .withHour(0)
            .withMinute(0)
            .withSecond(0),
        until: LocalDateTime = LocalDateTime.now()
    ): Result<Int?>
}
