package com.purewowstudio.bodystats.domain.healthdata.models

import androidx.health.connect.client.records.SleepStageRecord
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_AWAKE
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_DEEP
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_LIGHT
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_OUT_OF_BED
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_REM
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
    return getDurationFromStage(STAGE_TYPE_OUT_OF_BED)
}

fun SleepSession.getAwakeTime(): Duration {
    return getDurationFromStage(STAGE_TYPE_AWAKE)
}

fun SleepSession.getLightTime(): Duration {
    return getDurationFromStage(STAGE_TYPE_LIGHT)
}

fun SleepSession.getREMTime(): Duration {
    return getDurationFromStage(STAGE_TYPE_REM)
}

fun SleepSession.getDeepTime(): Duration {
    return getDurationFromStage(STAGE_TYPE_DEEP)
}

fun SleepSession.getTimeWentToBed(): LocalTime {
    return getLocalDateTime(startTime, startZoneOffset).toLocalTime()
}

fun SleepSession.getTimeOutOfBed(): LocalTime {
    return getLocalDateTime(endTime, endZoneOffset).toLocalTime()
}

fun SleepSession.getTimeInBed(): Duration {
    val startTime = getTimeWentToBed()
    val endTime = getTimeOutOfBed()
    return Duration.between(endTime, startTime)
}

private fun SleepSession.getDurationFromStage(stage: Int): Duration {
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