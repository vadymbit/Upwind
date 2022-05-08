package com.vadym.upwind.utils

import com.vadym.upwind.utils.Const.HOUR_FORMAT_24
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

object TimeUtils {
    fun convertUnixTimeToRegional(time: Long, timeFormat: Int, timeZone: String): String {
        val pattern = if (timeFormat == HOUR_FORMAT_24) {
            "H:mm"
        } else {
            "h a"
        }
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val instant = Instant.ofEpochSecond(time)
        return LocalDateTime.ofInstant(instant, ZoneId.of(timeZone)).format(formatter)
    }

    fun convertAstroTimeToRegional(time: String, timeFormat: Int): String {
        if (timeFormat == HOUR_FORMAT_24) {
            val formatter = DateTimeFormatter.ofPattern("H:mm")
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("h:mm a")).format(formatter)
        }
        return time
    }

    fun getDayOfWeekForUnixTime(time: Long): String {
        val instant = Instant.ofEpochSecond(time)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).dayOfWeek.getDisplayName(
            TextStyle.FULL,
            Locale.getDefault()
        ).replaceFirstChar { it.uppercaseChar() }
    }
}