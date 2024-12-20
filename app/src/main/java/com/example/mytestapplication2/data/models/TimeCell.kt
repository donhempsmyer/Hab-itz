package com.example.mytestapplication2.data.models

var timeList = mutableListOf<TimeCell>()

class TimeCell(
    val time: String,
    var timeContent: String,
    var id: Int? = timeList.size
)