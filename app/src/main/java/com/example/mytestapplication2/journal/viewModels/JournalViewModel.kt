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

    private val getJournalItemsById: LiveData<List<JournalItem>>
    private val journalRepository: JournalRepository

    private val _journalItemList = MutableLiveData<List<JournalItem>>()
    val journalItemList: LiveData<List<JournalItem>> get() = _journalItemList


    init {
        val journalDao = JournalDatabase.getDatabase(application).journalDao()
        journalRepository = JournalRepository(journalDao)
        getJournalItemsById = journalRepository.getJournalItemsById

        // Initialization of the journal item list
        val bookIcon = R.drawable.book_icon
        val startingEntryTitle = "First Journal Entry"
        val startingEntryBody = "This is the body of the first journal entry."
        val createdDate = SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault()).format(System.currentTimeMillis())

        _journalItemList.value = listOf(
            JournalItem(startingEntryTitle, createdDate, startingEntryBody),
            JournalItem("Second Journal Entry", "01-02-2025"),
            JournalItem("Third Journal Entry", "01-03-2025"),
            JournalItem("Fourth Journal Entry", "01-04-2025"),
            JournalItem("Fifth Journal Entry", "01-05-2025"),
            JournalItem("Sixth Journal Entry", "01-06-2025"),
            JournalItem("Seventh Journal Entry", "01-07-2025"),
            JournalItem("Eighth Journal Entry", "01-08-2025"),
            JournalItem("Ninth Journal Entry", "01-09-2025"),
            JournalItem("Tenth Journal Entry", "01-10-2025")
        )
    }

    fun upsertJournalItem(item: JournalItem) {
        viewModelScope.launch(Dispatchers.IO) {
            journalRepository.upsertJournalItem(item)
        }
    }
    // Add a journal item to the recycler list
    fun addJournalItem(itemToAdd: JournalItem) {

        val currentList = _journalItemList.value.orEmpty().toMutableList()
        currentList.add(itemToAdd)
        _journalItemList.value = currentList
    }

    fun removeJournalItem(itemToRemove: JournalItem): Boolean {

        val currentList = _journalItemList.value.orEmpty().toMutableList()
        val wasRemoved = currentList.remove(itemToRemove)
        _journalItemList.value = currentList

        return wasRemoved
    }

}