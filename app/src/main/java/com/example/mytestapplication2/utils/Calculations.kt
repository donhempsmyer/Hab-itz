package com.example.mytestapplication2.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object Calculations {

    fun calculateTimeBetweenDates(startDate: String): String {

        val endDate = timeStampToString(System.currentTimeMillis())

        val sdf = SimpleDateFormat("MM/dd/yyyy hh:mm a")
        val date1 = sdf.parse(startDate)
        val date2 = sdf.parse(endDate)

        var isNegative = false

        var difference = date2.time - date1.time

        if (difference < 0) {
            difference = -(difference)
            isNegative = true
        }

        val minutes = difference / 60 / 1000
        val hours = difference / 60 / 1000 / 60
        val days = (difference / 60 / 1000 / 60) / 24
        val months = (difference / 60 / 1000 / 60) / 24 / (365/12)
        val years = difference / 60 / 1000 / 60 / 24 / 365

        if (isNegative) {
            return when {
                minutes < 240 -> "Starts in $minutes minutes"
                hours < 48 -> "Starts in $hours hours"
                days < 61 -> "Starts in $days days"
                months < 12 -> "Starts in $months months"
                else -> "Starts in $years years"
            }
        }

        return when {
            minutes < 240 -> "$minutes minutes ago"
            hours < 48 -> "$hours hours ago"
            days < 61 -> "$days days ago"
            months < 12 -> "$months months ago"
            else -> "$years years ago"
        }
    }

    private fun timeStampToString(timestamp: Long): String {
        val stamp = Timestamp(timestamp)
        val sdf = SimpleDateFormat("MM/dd/yyyy hh:mm a")
        val date = sdf.format((Date(stamp.time)))

        return date.toString()
    }

    fun cleanDate(_day: Int, _month: Int, _year: Int) : String {
        var day = _day.toString()
        var month = _month.toString()

        if (_day < 10) {
            day = "0$_day"
        }
        if (_month < 9) {
            month = "0${_month+1}"
        }

        return "$day/$month/$_year"
    }

    fun cleanTime(_hour: Int, _minute: Int ): String {
        var hour = _hour.toString()
        var minute = _minute.toString()

        if (_hour < 10) {
            hour = "0$_hour"
        }

        if (_minute < 10) {
            minute = "0$_minute"
        }

        return "$hour:$minute"
    }
}