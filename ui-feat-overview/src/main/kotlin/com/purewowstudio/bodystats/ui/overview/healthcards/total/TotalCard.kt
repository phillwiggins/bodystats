package com.purewowstudio.bodystats.ui.overview.healthcards.total

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.components.dial.*
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.caloriesBurnt
import com.purewowstudio.bodystats.ui.common.theme.caloriesConsumed
import com.purewowstudio.bodystats.ui.common.theme.weight

@Composable
fun TotalCard() {
    TotalCardContent()
}

@Composable
internal fun TotalCardContent(
    caloriesUnderOrOver: Int = -200,
) {
    Dial(
        value = caloriesUnderOrOver,
        minValue = -500,
        maxValue = 500,
        modifier = Modifier
            .fillMaxWidth(),
        animation = ChartAnimation.Simple {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        },
        colors = DialColors(
            progressBarColor = MaterialTheme.colorScheme.weight,
            progressBarBackgroundColor = MaterialTheme.colorScheme.background,
            gridScaleColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)

        ),
        config = DialConfig(
            thickness = 12.dp,
            roundCorners = true,
        ),
        minAndMaxValueLabel = { value ->
            Text(
                text = "$value",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        mainLabel = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$it KCALS",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.weight
                )
                Text(
                    text = "UNDER GOAL",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun TotalCardPreview() {
    BodyStatsTheme {
        TotalCardContent()
    }
}