package com.purewowstudio.bodystats.data.healthconnect.data

import android.content.Context
import android.os.RemoteException
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.purewowstudio.bodystats.domain.base.di.IoDispatcher
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSteps
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

internal class HealthDataHeartImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : HealthDataSteps {

    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }

    override suspend fun readSteps(
        from: LocalDateTime,
        until: LocalDateTime
    ): Result<Int?> = withContext(dispatcher) {
        return@withContext try {
            val sessionTimeFilter = TimeRangeFilter.between(from, until)
            val stepsRequest = ReadRecordsRequest(
                recordType = StepsRecord::class,
                timeRangeFilter = sessionTimeFilter
            )
            val stepsResponse = healthConnectClient.readRecords(stepsRequest)
            Result.success(stepsResponse.records.firstOrNull()?.count?.toInt() ?: 0)
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