package com.purewowstudio.bodystats.domain.base

import java.time.Instant
import java.time.LocalDate
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