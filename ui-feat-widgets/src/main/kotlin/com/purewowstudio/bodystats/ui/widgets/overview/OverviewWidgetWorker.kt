package com.purewowstudio.bodystats.ui.widgets.overview

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.purewowstudio.bodystats.domain.healthdata.HealthDataCalories
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSteps
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class OverviewWidgetWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val healthDataSteps: HealthDataSteps,
    private val healthDataCalories: HealthDataCalories
) : CoroutineWorker(
    context,
    workerParameters
) {
    override suspend fun doWork(): Result {
        return try {
            val stepsResponse = healthDataSteps.readSteps()
            val caloriesResponse = healthDataCalories.readConsumed()
            updateOverviewWidget(
                steps = stepsResponse.getOrNull().toString(),
                burnt = caloriesResponse.getOrNull()?.burnt.toString(),
                consumed = caloriesResponse.getOrNull()?.consumed.toString()
            )
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(workDataOf("ERROR_MESSAGE" to e.localizedMessage))
        }
    }

    private suspend fun updateOverviewWidget(steps: String, burnt: String, consumed: String) {
        GlanceAppWidgetManager(context).getGlanceIds(OverviewWidget::class.java)
            .forEach { glanceId ->
                updateAppWidgetState(context, glanceId) { pref ->
                    pref[OverviewWidget.prefKeySteps] = steps
                    pref[OverviewWidget.prefKeyBurnt] = burnt
                    pref[OverviewWidget.prefKeyConsumed] = consumed
                }
                OverviewWidget().updateAll(context)
            }
    }

}