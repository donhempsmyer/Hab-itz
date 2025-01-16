package com.example.mytestapplication2.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytestapplication2.data.models.Habit

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query ("SELECT * FROM habit_table ORDER BY id DESC")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("DELETE FROM habit_table")
    suspend fun deleteAll()
}