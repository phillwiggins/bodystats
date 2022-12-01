package com.purewowstudio.bodystats.ui.widgets.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.currentState
import androidx.glance.layout.*
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.FixedColorProvider

class OverviewWidget : GlanceAppWidget() {
    override val stateDefinition: GlanceStateDefinition<*>
        get() = OverviewWidgetGlanceStateDefinition

    @Composable
    override fun Content() {
        val pref = currentState<Preferences>()

        val stepCount: String = remember {
            pref[prefKeySteps] ?: "0"
        }
        val burntKCalCount: String = remember {
            pref[prefKeyBurnt] ?: "0"
        }
        val consumedKCalCount: String = remember {
            pref[prefKeyConsumed] ?: "0"
        }

        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stepCount,
                    style = TextStyle(color = FixedColorProvider(Color.White), fontSize = 16.sp)
                )
                Text(
                    text = "Steps",
                    style = TextStyle(color = FixedColorProvider(Color.White), fontSize = 16.sp)
                )
            }
            Spacer(modifier = GlanceModifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = burntKCalCount,
                    style = TextStyle(color = FixedColorProvider(Color.White), fontSize = 16.sp)
                )
                Text(
                    text = "Burnt",
                    style = TextStyle(color = FixedColorProvider(Color.White), fontSize = 16.sp)
                )
            }
            Spacer(modifier = GlanceModifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = consumedKCalCount,
                    style = TextStyle(color = FixedColorProvider(Color.White), fontSize = 16.sp)
                )
                Text(
                    text = "Consumed",
                    style = TextStyle(color = FixedColorProvider(Color.White), fontSize = 16.sp)
                )
            }
        }
    }

    companion object {
        val prefKeySteps = stringPreferencesKey("key_steps")
        val prefKeyBurnt = stringPreferencesKey("key_burnt")
        val prefKeyConsumed = stringPreferencesKey("key_consumed")
    }
}

