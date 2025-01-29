package com.example.mytestapplication2.journal.data

import androidx.annotation.DrawableRes
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class JournalItem(
    @DrawableRes
    val itemImage: Int?,
    val itemTitle: String,
    val itemDate: String,
    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0
)