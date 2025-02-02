package com.example.mytestapplication2.journal.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.PrimaryKey
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "journal_table")
data class JournalItem(
    val itemTitle: String,
    val itemDate: String,
    val itemContent: String,
    @DrawableRes
    val itemImage: Int?,
    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0
): Parcelable