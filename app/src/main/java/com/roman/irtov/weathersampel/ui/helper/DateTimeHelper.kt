package com.roman.irtov.weathersampel.ui.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTimeHelper {

    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val durationFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    fun getDate(time: Long): String {
        return dateFormat.format(Date(time))
    }

    fun getDuration(time: Long): String {
        return durationFormat.format(Date(time))
    }
}