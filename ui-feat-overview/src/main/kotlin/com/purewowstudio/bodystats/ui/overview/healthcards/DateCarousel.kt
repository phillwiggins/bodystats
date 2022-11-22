package com.purewowstudio.bodystats.ui.overview.healthcards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun DateCarousel(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val dates = buildDateList()
    val listState = rememberLazyListState(dates.size)

    LazyRow(
        state = listState,
        modifier = modifier,
    ) {
        items(
            count = dates.size,
            itemContent = { index ->
                Row {
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(CircleShape)
                            .background(
                                if (dates[index].isEqual(selectedDate)) {
                                    MaterialTheme.colorScheme.onBackground
                                } else {
                                    Color.Transparent
                                }
                            )
                            .clickable { onDateSelected(dates[index]) },
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = dates[index].dayOfMonth.toString(),
                                color = if (dates[index].isEqual(selectedDate)) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onBackground
                                },
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = dates[index].dayOfWeek.toString().substring(0, 3),
                                color = if (dates[index].isEqual(selectedDate)) {
                                    MaterialTheme.colorScheme.onPrimary
                                } else {
                                    MaterialTheme.colorScheme.onBackground
                                },
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        )
    }
}

private fun buildDateList(): List<LocalDate> {
    val date2DaysAgo = LocalDate.now().plusDays(3)
    val dates = mutableListOf<LocalDate>()
    repeat(365) { index ->
        dates.add(date2DaysAgo.minusDays(index.toLong()))
    }
    return dates.reversed()
}