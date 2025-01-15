package com.example.mytestapplication2.data.models

import android.widget.EditText

var timeList = mutableListOf<TimeCell>()
var userTextList: ArrayList<EditText> = ArrayList<EditText>(48)


class TimeCell(
    val time: String,
    var timeContent: String,
    var id: Int? = timeList.size,

)