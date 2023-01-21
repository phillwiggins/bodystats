package com.purewowstudio.bodystats.ui.overview.healthcards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.components.ColourCard
import com.purewowstudio.bodystats.ui.common.theme.colorOnCustom

@Composable
fun OverviewCard(
    modifier: Modifier = Modifier,
    title: String,
    state: OverviewCardUiState,
    backgroundColor: Color,
    icon: ImageVector,
    onClicked: () -> Unit
) {
    ColourCard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        icon = icon,
        onClicked = onClicked
    ) {
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

        Spacer(modifier = Modifier.height(8.dp))

        when (state) {
            is OverviewCardUiState.Loading -> Row(
                modifier = Modifier
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