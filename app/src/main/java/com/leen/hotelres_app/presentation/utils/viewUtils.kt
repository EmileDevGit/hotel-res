package com.leen.hotelres_app.presentation.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

object viewUtils {
    fun convertirEpochEnDateFormattee(epochMillis: Long): String{
        val systemZoneId = ZoneId.systemDefault()
        val instant = Instant.ofEpochMilli(epochMillis)
        val formatter = DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
            .withLocale(Locale.getDefault())
        return instant.atZone(systemZoneId).toLocalDate().format(formatter)
    }
}