package com.vadym.upwind.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

object TimeUtils {
    fun convertUnixTimeToRegional(time: Long, is24hFormat: Boolean, timeZone: String): String {
        val pattern = if (is24hFormat) {
            "HH:mm"
        } else {
            "hh a"
        }
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val instant = Instant.ofEpochSecond(time)
        return LocalDateTime.ofInstant(instant, ZoneId.of(timeZone)).format(formatter)
    }

    fun getDayOfWeekForUnixTime(time: Long): String {
        val instant = Instant.ofEpochSecond(time)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).dayOfWeek.getDisplayName(
            TextStyle.FULL,
            Locale.getDefault()
        ).replaceFirstChar { it.uppercaseChar() }
    }
}