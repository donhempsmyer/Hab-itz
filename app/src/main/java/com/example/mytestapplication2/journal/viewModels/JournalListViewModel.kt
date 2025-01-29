package com.example.mytestapplication2.journal.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplication2.R
import com.example.mytestapplication2.journal.data.JournalItem
import com.example.mytestapplication2.journal.data.JournalEntry

class JournalListViewModel : ViewModel() {

    private val _journalItemList = MutableLiveData<List<JournalItem>>()
    val journalItemList: LiveData<List<JournalItem>> get() = _journalItemList

    private val _journalEntryList = MutableLiveData<List<JournalEntry>>()
    val journalEntryList: LiveData<List<JournalEntry>> get() = _journalEntryList

    private val _journalEntryTitle = MutableLiveData<String>()
    val journalEntryTitle: LiveData<String> get() = _journalEntryTitle

    private val _journalEntryBody = MutableLiveData<String>()
    val journalEntryBody: LiveData<String> get() = _journalEntryBody


    init {
        // Initialization of the journal item list
        val bookIcon = R.drawable.book_icon
        val startingEntryTitle = "First Journal Entry"
        val startingEntryDate = "01-01-2025"
        val startingEntryBody = "This is the body of the first journal entry."

        _journalItemList.value = listOf(
            JournalItem(bookIcon, startingEntryTitle, startingEntryDate),
            JournalItem(bookIcon, "Second Journal Entry", "01-02-2025"),
            JournalItem(bookIcon, "Third Journal Entry", "01-03-2025"),
            JournalItem(bookIcon, "Fourth Journal Entry", "01-04-2025"),
            JournalItem(bookIcon, "Fifth Journal Entry", "01-05-2025"),
            JournalItem(bookIcon, "Sixth Journal Entry", "01-06-2025"),
            JournalItem(bookIcon, "Seventh Journal Entry", "01-07-2025"),
            JournalItem(bookIcon, "Eighth Journal Entry", "01-08-2025"),
            JournalItem(bookIcon, "Ninth Journal Entry", "01-09-2025"),
            JournalItem(bookIcon, "Tenth Journal Entry", "01-10-2025")
        )

        _journalEntryList.value = listOf(
            JournalEntry(1, startingEntryTitle, startingEntryBody)
        )
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

    fun addJournalEntry(entryToAdd: JournalEntry) {

        val currentList = _journalEntryList.value.orEmpty().toMutableList()
        currentList.add(entryToAdd)
        _journalEntryList.value = currentList
    }

    fun removeJournalEntry(entryToRemove: JournalEntry): Boolean {

        val currentList = _journalEntryList.value.orEmpty().toMutableList()
        val wasRemoved = currentList.remove(entryToRemove)
        _journalEntryList.value = currentList

        return wasRemoved
    }


    // Add a title to the journal entry
    fun setJournalEntryTitle(title: String) {

        _journalEntryTitle.value = title
    }

    // Add a body to the journal entry
    fun setJournalEntryBody(body: String) {

        _journalEntryBody.value = body
    }
}