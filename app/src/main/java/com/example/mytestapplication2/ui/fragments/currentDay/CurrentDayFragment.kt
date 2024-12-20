package com.example.mytestapplication2.ui.fragments.currentDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mytestapplication2.databinding.FragmentCurrentDayBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CurrentDayFragment : Fragment() {

    private var _binding: FragmentCurrentDayBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCurrentDayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = binding.hourList


        val semiHourlyList: ArrayList<String> = ArrayList<String>()
        semiHourlyList.add("12:00:00 AM")
        semiHourlyList.add("12:30:00 AM")

        var hourVar = 1
        while (hourVar < 12) {
            semiHourlyList.add("$hourVar:00:00 AM")
            semiHourlyList.add("$hourVar:30:00 AM")
            hourVar ++

        }

        semiHourlyList.add("12:00:00 PM")
        semiHourlyList.add("12:30:00 PM")
        hourVar=1
        while (hourVar < 12) {
            semiHourlyList.add("$hourVar:00:00 PM")
            semiHourlyList.add("$hourVar:30:00 PM")
            hourVar ++

        }

        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, semiHourlyList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            Toast.makeText(requireContext(), "You clicked ${semiHourlyList.get(position)}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}