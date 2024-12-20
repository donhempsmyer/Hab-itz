package com.example.mytestapplication2.ui.fragments.habitlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentHabitzScreenBinding

class HabitzScreenFragment : Fragment(R.layout.fragment_habitz_screen) {

    private var _binding: FragmentHabitzScreenBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_HabitzScreenFragment_to_createHabitScreen)
        }

    }
}