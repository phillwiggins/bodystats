package com.purewowstudio.bodystats.data.healthconnect.data

import android.content.Context
import android.os.RemoteException
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.NutritionRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.request.AggregateRequest
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.purewowstudio.bodystats.domain.base.di.IoDispatcher
import com.purewowstudio.bodystats.domain.healthdata.HealthDataCalories
import com.purewowstudio.bodystats.domain.healthdata.models.CaloriesConsumed
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

internal class HealthDataCaloriesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : HealthDataCalories {

    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }

    override suspend fun readBurnt(from: LocalDateTime, until: LocalDateTime): Result<Int?> = withContext(dispatcher) {
        val timeUntilNow = if (until.isAfter(LocalDateTime.now())) LocalDateTime.now() else until
        return@withContext try {
            val sessionTimeFilter = TimeRangeFilter.between(from, timeUntilNow)
            val durationAggregateRequest = AggregateRequest(
                metrics = setOf(TotalCaloriesBurnedRecord.ENERGY_TOTAL),
                timeRangeFilter = sessionTimeFilter
            )
            val aggregateResponse = healthConnectClient.aggregate(durationAggregateRequest)

            val nutritionRequest = ReadRecordsRequest(
                recordType = TotalCaloriesBurnedRecord::class,
                timeRangeFilter = sessionTimeFilter
            )

            val response = healthConnectClient.readRecords(nutritionRequest)
            val record = response.records.firstOrNull()

            if (record == null) {
                Result.success(null)
            } else {
                Result.success(
                    aggregateResponse[TotalCaloriesBurnedRecord.ENERGY_TOTAL]?.inKilocalories?.toInt()
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

    override suspend fun readConsumed(
        from: LocalDateTime,
        until: LocalDateTime
    ): Result<CaloriesConsumed> = withContext(dispatcher) {
        return@withContext try {
            val sessionTimeFilter = TimeRangeFilter.between(from, until)
            val durationAggregateRequest = AggregateRequest(
                metrics = setOf(NutritionRecord.ENERGY_TOTAL),
                timeRangeFilter = sessionTimeFilter
            )
            val aggregateResponse = healthConnectClient.aggregate(durationAggregateRequest)

            val nutritionRequest = ReadRecordsRequest(
                recordType = NutritionRecord::class,
                timeRangeFilter = sessionTimeFilter
            )

            val response = healthConnectClient.readRecords(nutritionRequest)
            val record = response.records.firstOrNull()

            val burntResponse = readBurnt(from, until)
            val burntAmount = burntResponse.getOrNull() ?: 0
            
            Result.success(
                CaloriesConsumed(
                    burnt = burntAmount,
                    consumed = aggregateResponse[NutritionRecord.ENERGY_TOTAL]?.inKilocalories?.toInt()
                        ?: 0,
                    proteinGram = aggregateResponse[NutritionRecord.PROTEIN_TOTAL]?.inGrams?.toInt()
                        ?: 0,
                    carbGram = aggregateResponse[NutritionRecord.TOTAL_CARBOHYDRATE_TOTAL]?.inGrams?.toInt()
                        ?: 0,
                    fatGrm = aggregateResponse[NutritionRecord.TOTAL_FAT_TOTAL]?.inGrams?.toInt()
                        ?: 0,
                )
            )

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