package com.example.mytestapplication2.journal.data

import androidx.annotation.DrawableRes
import androidx.room.PrimaryKey
import androidx.room.Entity
import com.example.mytestapplication2.R

@Entity(tableName = "journal_table")
data class JournalItem(
    val itemTitle: String,
    val itemDate: String,
    val itemBody: String = "",
    @DrawableRes
    val itemImage: Int? = R.drawable.book_icon,
    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0
)