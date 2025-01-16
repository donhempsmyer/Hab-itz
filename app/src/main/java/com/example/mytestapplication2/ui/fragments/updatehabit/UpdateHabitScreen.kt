package com.example.mytestapplication2.ui.fragments.updatehabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytestapplication2.R
import com.example.mytestapplication2.data.models.Habit
import com.example.mytestapplication2.databinding.FragmentUpdateHabitScreenBinding
import com.example.mytestapplication2.ui.viewmodels.HabitViewModel
import com.example.mytestapplication2.utils.Calculations
import java.util.*

class UpdateHabitScreen : Fragment(R.layout.fragment_update_habit_screen),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var title = ""
    private var description = ""
    private var timeStamp = ""

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

        setHasOptionsMenu(true)
    }

    private fun updateHabitInDB() {
        title = binding.etHabitTitleUpdate.text.toString()
        description = binding.etHabitDescriptionUpdate.text.toString()
        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty())) {
            val habit = Habit(args.selectedHabit.id, title, description, timeStamp)

            habitViewModel.updateHabit(habit)
            Toast.makeText(context, "Habit updated successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateHabitScreen_to_HabitzScreenFragment)
        } else {
            Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> deleteHabit(args.selectedHabit)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteHabit(habit: Habit) {
        habitViewModel.deleteHabit(habit)
        Toast.makeText(context, "Habit successfully deleted!", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateHabitScreen_to_HabitzScreenFragment)
    }
}