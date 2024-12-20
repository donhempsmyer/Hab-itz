package com.example.mytestapplication2.data.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication2.databinding.RecyclerHabitItemBinding
import com.example.mytestapplication2.utils.Calculations


class HabitAdapter(private val habitList: List<Habit>) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    inner class HabitViewHolder(private val binding: RecyclerHabitItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(habit: Habit) {
            binding.tvHabitTitle.text = habit.habitTitle
            binding.tvHabitDescription.text = habit.habitDescription

            val timeElapsed = Calculations.calculateTimeBetweenDates(habit.habitStartTime)
            binding.tvTimeElapsed.text = timeElapsed

            binding.tvItemCreatedTimeStamp.text = "Created ${habit.habitStartTime}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = RecyclerHabitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.bind(habit)
    }

    override fun getItemCount(): Int = habitList.size
}
