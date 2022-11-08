package com.purewowstudio.bodystats.domain.healthdata.models

import androidx.health.connect.client.records.SleepStageRecord
import java.time.Instant
import java.time.ZoneOffset

data class SleepSession(
    val uid: String,
    val title: String?,
    val notes: String?,
    val startTime: Instant,
    val startZoneOffset: ZoneOffset?,
    val endTime: Instant,
    val endZoneOffset: ZoneOffset?,
    val duration: java.time.Duration?,
    val stages: List<SleepStageRecord> = listOf()
)