package com.purewowstudio.bodystats.data.healthconnect.data

import android.content.Context
import android.os.RemoteException
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.WeightRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.purewowstudio.bodystats.data.healthconnect.extensions.roundOffDecimal
import com.purewowstudio.bodystats.domain.base.di.IoDispatcher
import com.purewowstudio.bodystats.domain.base.toDayMonthYear
import com.purewowstudio.bodystats.domain.healthdata.HealthDataWeight
import com.purewowstudio.bodystats.domain.healthdata.models.CurrentWeight
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

internal class HealthDataWeightImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : HealthDataWeight {

    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }

    override suspend fun readLatestWeight(): Result<CurrentWeight?> = withContext(dispatcher) {
        return@withContext try {
            val request = ReadRecordsRequest(
                recordType = WeightRecord::class,
                timeRangeFilter = TimeRangeFilter.between(
                    LocalDateTime.now().minusYears(5),
                    LocalDateTime.now()
                )
            )
            val response = healthConnectClient.readRecords(request)
            val record = response.records.firstOrNull()

            if (record == null) {
                Result.success(null)
            } else {
                Result.success(
                    CurrentWeight(
                        dateRecorded = record.time.toDayMonthYear(),
                        amount = record.weight.inKilograms.roundOffDecimal().toString(),
                        type = "Kg"
                    )
                )
            }
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