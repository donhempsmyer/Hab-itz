package com.example.mytestapplication2.ui.fragments.updatehabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytestapplication2.R
import com.example.mytestapplication2.data.models.Habit
import com.example.mytestapplication2.databinding.FragmentUpdateHabitScreenBinding
import com.example.mytestapplication2.ui.viewmodels.HabitViewModel
import com.example.mytestapplication2.utils.Calculations
import com.google.android.material.snackbar.Snackbar
import java.util.*

class UpdateHabitScreen : Fragment(R.layout.fragment_update_habit_screen),
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
    private var _binding: FragmentUpdateHabitScreenBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateHabitScreenArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUpdateHabitScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        binding.etHabitTitleUpdate.setText(args.selectedHabit.habitTitle)
        binding.etHabitDescriptionUpdate.setText(args.selectedHabit.habitDescription)

        pickDateAndTime()

        binding.btnConfirmUpdate.setOnClickListener {
            updateHabitInDB()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnDeleteHabit.setOnClickListener {
            deleteHabit(args.selectedHabit)
        }
    }

    private fun updateHabitInDB() {
        title = binding.etHabitTitleUpdate.text.toString()
        description = binding.etHabitDescriptionUpdate.text.toString()
        timeStamp = "$cleanDate $cleanTime"
        errorMessage = binding.errorMessage.text.toString()

        if (title.isNotEmpty() && description.isNotEmpty() && cleanDate.isNotEmpty() && cleanTime.isNotEmpty()) {
            val habit = Habit(args.selectedHabit.id, title, description, timeStamp)

            habitViewModel.updateHabit(habit)

            findNavController().navigate(R.id.action_updateHabitScreen_to_HabitzScreenFragment)
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
        binding.btnPickDateUpdate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        binding.btnPickTimeUpdate.setOnClickListener {
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

    override fun onDateSet(p0: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {
        cleanDate = Calculations.cleanDate(dayX, monthX + 1, yearX)
        binding.tvDateSelectedUpdate.text = "Date: $cleanDate"
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        cleanTime = Calculations.cleanTime(p1, p2)
        binding.tvTimeSelectedUpdate.text = "Time: $cleanTime"
    }

    private fun deleteHabit(habit: Habit) {
        habitViewModel.deleteHabit(habit)
        val snackbar = Snackbar.make(binding.root, "Habit deleted successfully!", Snackbar.LENGTH_SHORT)
        val snackbarView = snackbar.view
        snackbar.setBackgroundTint(resources.getColor(R.color.habits_blue))
        snackbar.setTextColor(resources.getColor(R.color.habits_green, null))
        val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(params.leftMargin, params.topMargin + 200, params.rightMargin, params.bottomMargin + 200)
        snackbarView.layoutParams = params
        snackbar.show()
        findNavController().navigate(R.id.action_updateHabitScreen_to_HabitzScreenFragment)
    }
}