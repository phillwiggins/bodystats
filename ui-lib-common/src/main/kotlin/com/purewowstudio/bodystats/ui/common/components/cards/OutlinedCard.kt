package com.purewowstudio.bodystats.ui.common.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.common.theme.colorOnCustom

@Composable
fun OutlinedCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClicked: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    androidx.compose.material3.OutlinedCard(
        modifier = modifier
            .clickable(enabled = onClicked != null, onClick = { onClicked?.invoke() })
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight(),
                imageVector = icon,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Column(
                modifier = Modifier.weight(1F),
                content = content
            )
        }
    }
}

@Preview
@Composable
fun OutlinedCardPreview() {
    BodyStatsTheme {
        OutlinedCard(
            modifier = Modifier
                .height(100.dp)
                .width(200.dp),
            icon = Icons.Filled.RunCircle
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "TEST")
                Text(text = "TEST2222")
            }
        }
    }
}