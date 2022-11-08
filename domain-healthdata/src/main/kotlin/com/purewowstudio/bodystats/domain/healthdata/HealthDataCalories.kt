package com.purewowstudio.bodystats.domain.healthdata

import java.time.LocalDateTime

interface HealthDataCalories {
    suspend fun readConsumedToday(
        from: LocalDateTime = LocalDateTime.now()
            .withHour(0)
            .withMinute(0)
            .withSecond(0),
        until: LocalDateTime = LocalDateTime.now()
    ): Result<Int?>

    suspend fun readBurntToday(
        from: LocalDateTime = LocalDateTime.now()
            .withHour(0)
            .withMinute(0)
            .withSecond(0),
        until: LocalDateTime = LocalDateTime.now()
    ): Result<Int?>
}
