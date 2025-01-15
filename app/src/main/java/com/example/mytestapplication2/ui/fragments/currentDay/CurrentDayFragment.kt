package com.example.mytestapplication2.ui.fragments.currentDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapplication2.data.models.TimeCell
import com.example.mytestapplication2.data.models.timeList
import com.example.mytestapplication2.databinding.FragmentCurrentDayBinding


class CurrentDayFragment : Fragment() {

    private lateinit var binding: FragmentCurrentDayBinding
    lateinit var eventText: EditText





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentCurrentDayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateDay()


        val CurrentDayFragment = this
        binding.mHalfHourRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TimeAdapter(timeList)
        }



    }

    override fun onResume() {
        super.onResume()

    }




    private fun populateDay() {
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
        for (i in semiHourlyList.indices) {

            var timeCell = (TimeCell(
                time = semiHourlyList[i],
                timeContent = ""))
            timeList.add(timeCell)
        }
    }


}