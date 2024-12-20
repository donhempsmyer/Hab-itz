package com.example.mytestapplication2.ui.fragments.habitlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytestapplication2.R
import com.example.mytestapplication2.data.models.Habit
import com.example.mytestapplication2.data.models.HabitAdapter
import com.example.mytestapplication2.databinding.FragmentHabitzScreenBinding

class HabitzScreenFragment : Fragment(R.layout.fragment_habitz_screen) {

    private var _binding: FragmentHabitzScreenBinding? = null
    private val binding get() = _binding!!

    private val habitList = mutableListOf<Habit>()

    private lateinit var habitAdapter: HabitAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHabitzScreenBinding.bind(view)

        habitAdapter = HabitAdapter(habitList)

        binding.rvHabits.adapter = habitAdapter

        updateEmptyView()

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_HabitzScreenFragment_to_createHabitScreen)
            updateEmptyView()
        }

    }

    private fun updateEmptyView() {
        if (habitAdapter.itemCount == 0) {
            binding.tvEmptyView.visibility = View.VISIBLE
            binding.rvHabits.visibility = View.GONE
        }
        else {
            binding.tvEmptyView.visibility = View.GONE
            binding.rvHabits.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}