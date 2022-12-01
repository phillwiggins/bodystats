package com.purewowstudio.bodystats

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.purewowstudio.bodystats.ui.main.MainScreen
import com.purewowstudio.bodystats.ui.widgets.overview.OverviewWidgetWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startWidgetWorkers()

        setContent {
            MainScreen()
        }
    }

    private fun startWidgetWorkers() {
        val constraints = Constraints.Builder().build()
        val work = PeriodicWorkRequestBuilder<OverviewWidgetWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(work)
    }
}