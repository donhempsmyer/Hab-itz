package com.example.mytestapplication2.ui.fragments.habitlist

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapplication2.R
import com.example.mytestapplication2.data.models.Habit
import com.example.mytestapplication2.databinding.FragmentHabitzScreenBinding
import com.example.mytestapplication2.ui.fragments.habitlist.adapters.HabitAdapter
import com.example.mytestapplication2.ui.viewmodels.HabitViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

class HabitzScreenFragment : Fragment(R.layout.fragment_habitz_screen) {

    private var _binding: FragmentHabitzScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var habitList: List<Habit>
    private lateinit var habitViewModel: HabitViewModel
    private lateinit var habitAdapter: HabitAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHabitzScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter
        habitAdapter = HabitAdapter()
        binding.rvHabits.adapter = habitAdapter
        binding.rvHabits.layoutManager = LinearLayoutManager(context)

        // ViewModel
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        habitViewModel.getAllHabits.observe(viewLifecycleOwner, Observer { habits ->

            habitAdapter.setData(habits)
            habitList = habits

            if (habits.isEmpty()) {
                binding.rvHabits.visibility = View.GONE
                binding.tvEmptyView.visibility = View.VISIBLE
            } else {
                binding.rvHabits.visibility = View.VISIBLE
                binding.tvEmptyView.visibility = View.GONE
            }
        })

        binding.swipeToRefresh.setOnRefreshListener {
            habitAdapter.setData(habitList)
            binding.swipeToRefresh.isRefreshing = false
        }

        val savedColor = getSavedColor()
        binding.rvHabits.setBackgroundColor(savedColor)

        binding.btnDeleteAll.setOnClickListener {
            showDeleteAllDialog()
        }

        binding.btnSelectColor.setOnClickListener {
            colorPickerDialog()
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_HabitzScreenFragment_to_createHabitScreen)
        }
    }

    private fun showDeleteAllDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete All Habits")
        builder.setMessage("Are you sure you want to delete all habits?")
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setPositiveButton("Yes") { _, _ ->
            habitViewModel.deleteAllHabits()
            val snackbar = Snackbar.make(binding.root, "All habits deleted successfully!", Snackbar.LENGTH_SHORT)
            val snackbarView = snackbar.view
            snackbar.setBackgroundTint(resources.getColor(R.color.habits_blue))
            snackbar.setTextColor(resources.getColor(R.color.habits_green, null))
            val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin + 2100)
            snackbarView.layoutParams = params
            snackbar.show()
        }
        builder.create().show()
    }

    private fun colorPickerDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_color_picker, null)
        val colorPreview = dialogView.findViewById<View>(R.id.color_preview)
        val redSeekBar = dialogView.findViewById<SeekBar>(R.id.red_seekbar)
        val greenSeekBar = dialogView.findViewById<SeekBar>(R.id.green_seekbar)
        val blueSeekBar = dialogView.findViewById<SeekBar>(R.id.blue_seekbar)

        val updateColorPreview = {
            val color = Color.rgb(redSeekBar.progress, greenSeekBar.progress, blueSeekBar.progress)
            colorPreview.setBackgroundColor(color)
        }

        redSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        greenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        blueSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        AlertDialog.Builder(requireContext())
            .setTitle("Pick a color")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val color = Color.rgb(redSeekBar.progress, greenSeekBar.progress, blueSeekBar.progress)
                binding.rvHabits.setBackgroundColor(color)
                saveSelectedColor(color)
            }
            .setNegativeButton("Cancel", null)
            .setNeutralButton("Reset Color") { _, _ ->
                val defaultColor = resources.getColor(R.color.habits_green, null)
                binding.rvHabits.setBackgroundColor(defaultColor)
                saveSelectedColor(defaultColor)
            }
            .show()
    }

    private fun saveSelectedColor(color: Int) {
        val sharedPref = requireContext().getSharedPreferences("color_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("color", color)
            apply()
        }
    }

    private fun getSavedColor(): Int {
        val sharedPref = requireContext().getSharedPreferences("color_prefs", Context.MODE_PRIVATE)
        return sharedPref.getInt("color", resources.getColor(R.color.habits_green, null))
    }
}