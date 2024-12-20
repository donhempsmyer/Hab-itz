package com.example.mytestapplication2.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habit (
    val id: Int,
    val habitTitle: String,
    val habitDescription: String = "",
    val habitStartTime: String,
    val imageId: Int? = null,
) : Parcelable