package com.purewowstudio.bodystats.domain.base

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
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