package com.example.mytestapplication2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapplication2.databinding.FragmentHabitzScreenBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HabitzScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitzScreenFragment : Fragment() {

    private var _binding: FragmentHabitzScreenBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habitz_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}