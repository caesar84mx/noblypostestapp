package com.caesar_84.noblypostestapp.commons.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateHelper {
    fun getDateString(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return date.format(formatter)
    }
}