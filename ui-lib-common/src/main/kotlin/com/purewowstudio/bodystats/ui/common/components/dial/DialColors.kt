package com.purewowstudio.bodystats.ui.common.components.dial

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class DialColors(
    val progressBarColor: Color,
    val progressBarBackgroundColor: Color,
    val gridScaleColor: Color,
)

val ChartColors.dialColors
    get() = DialColors(
        progressBarColor = primary,
        progressBarBackgroundColor = grid,
        gridScaleColor = grid
    )
