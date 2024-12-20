package com.example.mytestapplication2.ui.fragments.YearView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentYearlyViewBinding


/**
 * A simple [Fragment] subclass.
 * Use the [YearlyViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YearlyViewFragment : Fragment() {

    private var _binding: FragmentYearlyViewBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yearly_view, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}