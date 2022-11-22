package com.purewowstudio.bodystats.data.healthconnect.data

import android.content.Context
import android.os.RemoteException
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.SleepStageRecord
import androidx.health.connect.client.request.AggregateRequest
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSleep
import com.purewowstudio.bodystats.domain.healthdata.models.SleepSession
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

internal class HealthDataSleepImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : HealthDataSleep {

    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }

    override suspend fun readSleepSessions(
        from: LocalDateTime,
        until: LocalDateTime
    ): Result<List<SleepSession>> {

        val sessions = mutableListOf<SleepSession>()
        val sleepSessionRequest = ReadRecordsRequest(
            recordType = SleepSessionRecord::class,
            timeRangeFilter = TimeRangeFilter.between(
                from.minusHours(12),
                until
            ),
            ascendingOrder = false
        )

        return try {
            val sleepSessions = healthConnectClient.readRecords(sleepSessionRequest)

            sleepSessions.records.forEach { session ->
                val sessionTimeFilter = TimeRangeFilter.between(session.startTime, session.endTime)
                val durationAggregateRequest = AggregateRequest(
                    metrics = setOf(SleepSessionRecord.SLEEP_DURATION_TOTAL),
                    timeRangeFilter = sessionTimeFilter
                )
                val aggregateResponse = healthConnectClient.aggregate(durationAggregateRequest)

                val stagesRequest = ReadRecordsRequest(
                    recordType = SleepStageRecord::class,
                    timeRangeFilter = sessionTimeFilter
                )
                val stagesResponse = healthConnectClient.readRecords(stagesRequest)
                sessions.add(
                    SleepSession(
                        uid = session.metadata.id,
                        title = session.title,
                        notes = session.notes,
                        startTime = session.startTime,
                        startZoneOffset = session.startZoneOffset,
                        endTime = session.endTime,
                        endZoneOffset = session.endZoneOffset,
                        duration = aggregateResponse[SleepSessionRecord.SLEEP_DURATION_TOTAL],
                        stages = stagesResponse.records
                    )
                )
            }
            Result.success(sessions)
        } catch (exception: RemoteException) {
            Result.failure(exception)
        } catch (exception: SecurityException) {
            Result.failure(exception)
        } catch (exception: IOException) {
            Result.failure(exception)
        } catch (exception: IllegalStateException) {
            Result.failure(exception)
        }
    }
}