package com.example.mytestapplication2.ui.fragments.createhabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mytestapplication2.R
import com.example.mytestapplication2.data.models.Habit
import com.example.mytestapplication2.databinding.FragmentCreateHabitScreenBinding
import com.example.mytestapplication2.ui.viewmodels.HabitViewModel
import com.example.mytestapplication2.utils.Calculations
import java.util.*

class CreateHabitScreen : Fragment(R.layout.fragment_create_habit_screen),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var title = ""
    private var description = ""
    private var timeStamp = ""
    private var errorMessage = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var habitViewModel: HabitViewModel
    private var _binding: FragmentCreateHabitScreenBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateHabitScreenBinding.bind(view)
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)


        binding.btnConfirm.setOnClickListener {
            addHabitToDB()
        }

        pickDateAndTime()
    }

    private fun addHabitToDB() {
        title = binding.etHabitTitle.text.toString()
        description = binding.etHabitDescription.text.toString()
        timeStamp = "$cleanDate $cleanTime"
        errorMessage = binding.errorMessage.text.toString()

        if (title.isNotEmpty() && description.isNotEmpty() && cleanDate.isNotEmpty() && cleanTime.isNotEmpty()) {
            val habit = Habit(0, title, description, timeStamp)

            habitViewModel.addHabit(habit)

            findNavController().navigate(R.id.action_createHabitScreen_to_HabitzScreenFragment)
        } else {
            if (title.isEmpty() || description.isEmpty() || cleanDate.isEmpty() || cleanTime.isEmpty()) {
                val textView = TextView(context)
                textView.text = "Error: Please fill out all fields!"
                textView.gravity = Gravity.TOP
                textView.textSize = 20f
                binding.errorMessage.text = textView.text
            }
        }
    }

    private fun pickDateAndTime() {
        binding.btnPickDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        binding.btnPickTime.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute, true).show()
        }
    }

    private fun getTimeCalendar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onTimeSet(view: TimePicker?, p1: Int, p2: Int) {
        cleanTime = Calculations.cleanTime(p1, p2)
        binding.tvTimeSelected.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {
        cleanDate = Calculations.cleanDate(dayX, monthX + 1, yearX)
        binding.tvDateSelected.text = "Date: $cleanDate"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
