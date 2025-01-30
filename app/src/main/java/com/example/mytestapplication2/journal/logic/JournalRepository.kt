package com.example.mytestapplication2.journal.logic

import androidx.lifecycle.LiveData
import com.example.mytestapplication2.journal.data.JournalItem

class JournalRepository(private val journalDao: JournalDao) {

    val getJournalItemsById: LiveData<List<JournalItem>> = journalDao.getJournalItemsById()
    val getJournalItemsByDate: LiveData<List<JournalItem>> = journalDao.getJournalItemsByDate()
    val getJournalItemsByTitle: LiveData<List<JournalItem>> = journalDao.getJournalItemsByTitle()

    suspend fun upsertJournalItem(item: JournalItem) {
        journalDao.upsertJournalItem(item)
    }

    suspend fun deleteJournalItem(item: JournalItem) {
        journalDao.deleteJournalItem(item)
    }

    suspend fun deleteAllJournalItems() {
        journalDao.deleteAllJournalItems()
    }
}