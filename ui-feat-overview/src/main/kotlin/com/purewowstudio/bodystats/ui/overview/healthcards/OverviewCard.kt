package com.purewowstudio.bodystats.ui.overview.healthcards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.theme.colorOnCustom

@Composable
fun OverviewCard(
    modifier: Modifier = Modifier,
    title: String,
    state: OverviewCardUiState,
    backgroundColor: Color,
    icon: ImageVector
) {
    ElevatedCard(modifier = modifier.height(160.dp)) {
        Box(
            modifier = modifier
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
            Column(modifier = modifier.padding(12.dp)) {
                Column {
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
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        color = MaterialTheme.colorScheme.colorOnCustom,
                        style = MaterialTheme.typography.titleMedium,
                        text = title
                    )
                    if ((state is OverviewCardUiState.Loaded) && state.subtitle != null) {
                        Text(
                            color = MaterialTheme.colorScheme.colorOnCustom,
                            style = MaterialTheme.typography.titleSmall,
                            text = state.subtitle
                        )
                    }
                }

                Spacer(modifier = modifier.height(8.dp))

                when (state) {
                    is OverviewCardUiState.Loading -> Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                    OverviewCardUiState.Error, OverviewCardUiState.NoData -> Text(
                        color = MaterialTheme.colorScheme.colorOnCustom,
                        style = MaterialTheme.typography.headlineMedium,
                        text = "-"
                    )
                    is OverviewCardUiState.Loaded -> Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        val amountTextStyle = MaterialTheme.typography.headlineSmall
                        val typeTextStyle = MaterialTheme.typography.bodySmall
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = amountTextStyle.fontSize,
                                        color = MaterialTheme.colorScheme.colorOnCustom,
                                        fontStyle = amountTextStyle.fontStyle,
                                        fontFamily = amountTextStyle.fontFamily,
                                        fontWeight = amountTextStyle.fontWeight
                                    )
                                ) {
                                    append(state.amount)
                                }
                                append(" ")
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = typeTextStyle.fontSize,
                                        color = MaterialTheme.colorScheme.colorOnCustom,
                                        fontStyle = typeTextStyle.fontStyle,
                                        fontFamily = typeTextStyle.fontFamily,
                                        fontWeight = typeTextStyle.fontWeight
                                    )
                                ) {
                                    append(state.type)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}