package com.purewowstudio.bodystats.domain.base

import android.os.Build
import java.time.*
import java.time.format.DateTimeFormatter

fun Instant.toDayMonthYear(): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE dd . MMM yy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(this)
}

fun LocalDate.toDayMonthYear(): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE dd . MMM yy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(this)
}

fun LocalDate.toDateOfBirth(): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(this)
}

fun LocalDateTime.toSimpleTime(): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mma")
        .withZone(ZoneId.systemDefault())
    return formatter.format(this)
}

fun LocalDate.getDateTimeAtStartOfDay(): LocalDateTime {
    return LocalDateTime.now()
        .withYear(year)
        .withDayOfYear(dayOfYear)
        .withMinute(0)
        .withHour(0)
        .withSecond(0)
        .withNano(0)
}

fun LocalDate.getDateTimeAtEndOfDay(): LocalDateTime {
    return LocalDateTime.now()
        .withYear(year)
        .withDayOfYear(dayOfYear)
        .withMinute(59)
        .withHour(23)
        .withSecond(59)
}

fun Duration.toFormattedDuration(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        String.format(
            "%dh %02dm",
            this.toHours(),
            this.toMinutesPart(),
        )
    } else {
        "0h, 00m"
    }
}