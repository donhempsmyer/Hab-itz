package com.example.mytestapplication2.data.models

var timeList = mutableListOf<TimeCell>()
var userTextList: ArrayList<String> = ArrayList<String>(48)



class TimeCell(
    val time: String,
    var timeContent: String,
    var id: Int? = timeList.size,

)