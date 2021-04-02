package com.example.testformobiledeveloper.util

import com.example.testformobiledeveloper.ui.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.*

enum class DateFormat(val format: String) {
    DD("dd"),
    EE("EE"),
}

object DateUtils {

    fun getDayOfMonth(dayOfWeek: Int): String {
        Calendar.getInstance().let { calendar ->
            calendar.timeInMillis = Date().time
            var currentDay = calendar.get(Calendar.DAY_OF_WEEK)
            if (currentDay == Calendar.SUNDAY) {
                currentDay = 8
            }
            calendar.add(Calendar.DAY_OF_MONTH, dayOfWeek - currentDay)
            val callbackMillisecond = calendar.timeInMillis
            if (callbackMillisecond <= 0L) {
                return ""
            }
            val df = SimpleDateFormat(DateFormat.DD.format, Locale.ENGLISH)
            df.timeZone = TimeZone.getTimeZone("GMT")
            return df.format(callbackMillisecond) ?: ""
        }
    }

    fun getCurrentDayOfWeek(): Int {
        Calendar.getInstance().let { calendar ->
            calendar.timeInMillis = Date().time
            return if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                calendar.get(Calendar.DAY_OF_WEEK)
            } else {
                8
            }
        }
    }
}
