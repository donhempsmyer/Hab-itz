package com.example.mytestapplication2.journal.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.mytestapplication2.R
import com.example.mytestapplication2.journal.data.JournalDatabase
import com.example.mytestapplication2.journal.data.JournalItem
import com.example.mytestapplication2.journal.logic.JournalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class JournalViewModel(application: Application) : AndroidViewModel(application) {

    val getJournalItemsById: LiveData<List<JournalItem>>
    private val journalRepository: JournalRepository

    init {

        val journalDao = JournalDatabase.getDatabase(application).journalDao()
        journalRepository = JournalRepository(journalDao)
        getJournalItemsById = journalRepository.getJournalItemsById
    }

    fun upsertJournalItem(item: JournalItem) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepository.upsertJournalItem(item)
        }
    }

    fun deleteJournalItem(item: JournalItem) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepository.deleteJournalItem(item)
        }
    }

    fun deleteAllJournalItems() {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepository.deleteAllJournalItems()
        }
    }
}