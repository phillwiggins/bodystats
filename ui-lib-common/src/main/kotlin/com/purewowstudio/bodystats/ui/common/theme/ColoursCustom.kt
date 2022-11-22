package com.purewowstudio.bodystats.ui.common.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val customColorConstructive = Color(0xFF1CCC41)
private val customColorDestructive = Color(0xFFE40808)

private val customColorWeightLight = Color(0xFF38b552)
private val customColorWeightDark = Color(0xFF000000)

private val customColorSleepLight = Color(0xFF5975ed)
private val customColorSleepDark = Color(0xFF000000)

private val customColorBurntLight = Color(0xFFf3a65e)
private val customColorBurntDark = Color(0xFF000000)

private val customColorConsumedLight = Color(0xFF58b7e9)
private val customColorConsumedDark = Color(0xFF000000)

private val customColorStepsLight = Color(0xFFc861fa)
private val customColorStepsDark = Color(0xFF000000)

private val customColorPrimaryText = Color(0xFFffffff)

@get:Composable
val ColorScheme.constructive: Color
    get() =  customColorConstructive

@get:Composable
val ColorScheme.destructive: Color
    get() =  customColorDestructive

@get:Composable
val ColorScheme.caloriesBurnt: Color
    get() =  customColorBurntLight

@get:Composable
val ColorScheme.caloriesConsumed: Color
    get() = customColorConsumedLight

@get:Composable
val ColorScheme.weight: Color
    get() = customColorWeightLight

@get:Composable
val ColorScheme.sleep: Color
    get() = customColorSleepLight

@get:Composable
val ColorScheme.steps: Color
    get() = customColorStepsLight

@get:Composable
val ColorScheme.colorOnCustom: Color
    get() = customColorPrimaryText