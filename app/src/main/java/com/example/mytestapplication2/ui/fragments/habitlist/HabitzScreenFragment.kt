package com.example.mytestapplication2.ui.fragments.habitlist

import android.os.Bundle
import android.view.*
import android.widget.Toast
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

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_HabitzScreenFragment_to_createHabitScreen)
        }
    }
}