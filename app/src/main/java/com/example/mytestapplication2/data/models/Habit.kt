package com.example.mytestapplication2.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "habit_table")
data class Habit (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val habitTitle: String,
    val habitDescription: String,
    val habitStartTime: String,
) : Parcelable