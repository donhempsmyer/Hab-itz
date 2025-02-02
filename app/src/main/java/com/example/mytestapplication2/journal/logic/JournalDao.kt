package com.example.mytestapplication2.journal.logic

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytestapplication2.journal.data.JournalItem

@Dao
interface JournalDao {

    // Insert a journal item or update it if it already exists
    @Upsert
    suspend fun upsertJournalItem(journalItem: JournalItem)

    // Delete a journal item
    @Delete
    suspend fun deleteJournalItem(journalItem: JournalItem)

    // Get all journal items sorted by date
    @Query("SELECT * FROM journal_table ORDER BY itemDate ASC")
    fun getJournalItemsByDate(): LiveData<List<JournalItem>>

    // Get all journal items sorted by title
    @Query("SELECT * FROM journal_table ORDER BY itemTitle ASC")
    fun getJournalItemsByTitle(): LiveData<List<JournalItem>>

    // Get all journal items sorted by id
    @Query("SELECT * FROM journal_table ORDER BY itemId ASC")
    fun getJournalItemsById(): LiveData<List<JournalItem>>

    // Delete all journal items
    @Query("DELETE FROM journal_table")
    suspend fun deleteAllJournalItems()
}