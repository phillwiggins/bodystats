package com.purewowstudio.bodystats.ui.widgets.overview

import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.purewowstudio.bodystats.ui.widgets.overview.OverviewWidget

class BodyStatsWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = OverviewWidget()
}