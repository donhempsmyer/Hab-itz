package com.example.mytestapplication2.journal.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplication2.R
import com.example.mytestapplication2.journal.data.JournalItem

class JournalListViewModel : ViewModel() {

    private val _journalItemList = MutableLiveData<List<JournalItem>>()
    val journalItemList: LiveData<List<JournalItem>> get() = _journalItemList

    init {
        // Initialization of the journal item list
        _journalItemList.value = listOf(
            JournalItem(1, R.drawable.book_icon,"First Journal Entry", "01-01-2025"),
            JournalItem(2, R.drawable.book_icon,"Second Journal Entry", "01-02-2025"),
            JournalItem(3, R.drawable.book_icon,"Third Journal Entry", "01-03-2025"),
            JournalItem(4, R.drawable.book_icon,"Fourth Journal Entry", "01-04-2025"),
            JournalItem(5, R.drawable.book_icon,"Fifth Journal Entry", "01-05-2025"),
            JournalItem(6, R.drawable.book_icon,"Sixth Journal Entry", "01-06-2025"),
            JournalItem(7, R.drawable.book_icon,"Seventh Journal Entry", "01-07-2025"),
            JournalItem(8, R.drawable.book_icon,"Eighth Journal Entry", "01-08-2025"),
            JournalItem(9, R.drawable.book_icon,"Ninth Journal Entry", "01-09-2025"),
            JournalItem(10, R.drawable.book_icon,"Tenth Journal Entry", "01-10-2025")
        )
    }

    fun addJournalItem(journalItem: JournalItem) {

        val currentList = _journalItemList.value.orEmpty().toMutableList()
        currentList.add(journalItem)
        _journalItemList.value = currentList
    }
}