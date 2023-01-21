package com.purewowstudio.bodystats.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.theme.colorOnCustom

@Composable
fun ColourCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: ImageVector,
    onClicked: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .clickable(enabled = onClicked != null, onClick = { onClicked?.invoke() })
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            backgroundColor.copy(alpha = 0.75F),
                            backgroundColor
                        )
                    )
                )
                .fillMaxSize()
        ) {
            Column(Modifier.padding(12.dp)) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(color = Color.White.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.colorOnCustom
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(content = content)
            }
        }
    }
}