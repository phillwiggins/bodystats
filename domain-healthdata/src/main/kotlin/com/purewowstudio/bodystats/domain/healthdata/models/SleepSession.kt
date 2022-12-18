package com.purewowstudio.bodystats.domain.healthdata.models

import androidx.health.connect.client.records.SleepStageRecord
import androidx.health.connect.client.records.SleepStageRecord.StageType.AWAKE
import androidx.health.connect.client.records.SleepStageRecord.StageType.DEEP
import androidx.health.connect.client.records.SleepStageRecord.StageType.LIGHT
import androidx.health.connect.client.records.SleepStageRecord.StageType.OUT_OF_BED
import androidx.health.connect.client.records.SleepStageRecord.StageType.REM
import java.time.*

data class SleepSession(
    val uid: String,
    val title: String?,
    val notes: String?,
    val startTime: Instant,
    val startZoneOffset: ZoneOffset?,
    val endTime: Instant,
    val endZoneOffset: ZoneOffset?,
    val duration: Duration?,
    val stages: List<SleepStageRecord> = listOf()
)

fun SleepSession.getOutOfBedTime(): Duration {
    return getDurationFromStage(OUT_OF_BED)
}

fun SleepSession.getAwakeTime(): Duration {
    return getDurationFromStage(AWAKE)
}

fun SleepSession.getLightTime(): Duration {
    return getDurationFromStage(LIGHT)
}

fun SleepSession.getREMTime(): Duration {
    return getDurationFromStage(REM)
}

fun SleepSession.getDeepTime(): Duration {
    return getDurationFromStage(DEEP)
}

private fun SleepSession.getDurationFromStage(stage: String): Duration {
    val awakeItems = stages.filter { it.stage == stage }
    return awakeItems.fold(Duration.ZERO) { sum, element ->
        val startTime = getLocalDateTime(element.startTime, element.startZoneOffset)
        val endTime = getLocalDateTime(element.endTime, element.endZoneOffset)
        sum.plus(Duration.between(startTime, endTime))
    }
}

private fun getLocalDateTime(instant: Instant, zoneOffset: ZoneOffset?): LocalDateTime {
    return LocalDateTime.ofInstant(instant, zoneOffset ?: ZoneId.systemDefault())
}