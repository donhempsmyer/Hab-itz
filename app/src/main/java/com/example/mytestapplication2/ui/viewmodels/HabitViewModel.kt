package com.example.mytestapplication2.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapplication2.data.models.Habit

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val _habits = MutableLiveData<MutableList<Habit>>(mutableListOf())
    val habits: LiveData<MutableList<Habit>> get() = _habits

    fun addHabit(habit: Habit) {
        _habits.value?.add(habit)
        _habits.value = _habits.value
    }

    fun deleteHabit(habit: Habit) {
        _habits.value?.remove(habit)
        _habits.value = _habits.value
    }
}