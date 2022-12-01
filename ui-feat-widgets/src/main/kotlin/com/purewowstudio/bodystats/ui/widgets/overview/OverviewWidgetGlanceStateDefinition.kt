package com.purewowstudio.bodystats.ui.widgets.overview

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.glance.state.GlanceStateDefinition
import java.io.File

object OverviewWidgetGlanceStateDefinition : GlanceStateDefinition<Preferences> {
    private const val fileName = "widget_overview_preference"

    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<Preferences> {
        return context.dataStore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return File(context.applicationContext.filesDir, "datastore/$fileName")
    }

    private val Context.dataStore: DataStore<Preferences>
            by preferencesDataStore(name = fileName)
}