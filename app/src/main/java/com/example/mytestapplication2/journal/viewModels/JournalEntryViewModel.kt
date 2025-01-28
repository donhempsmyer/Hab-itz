package com.example.mytestapplication2.journal.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JournalEntryViewModel : ViewModel() {
    private val _journalEntryTitle = MutableLiveData<String>()
    val journalEntryTitle: LiveData<String> get() = _journalEntryTitle

    private val _journalEntryBody = MutableLiveData<String>()
    val journalEntryBody: LiveData<String> get() = _journalEntryBody

    fun setJournalEntryTitle(title: String) {
        _journalEntryTitle.value = title
    }

    fun setJournalEntryBody(body: String) {
        _journalEntryBody.value = body
    }
}