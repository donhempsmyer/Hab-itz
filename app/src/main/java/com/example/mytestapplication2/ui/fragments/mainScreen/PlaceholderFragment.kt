package com.example.mytestapplication2.ui.fragments.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mytestapplication2.R

class PlaceholderFragment : Fragment() {

    private lateinit var  textView : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_placeholder, container, false)

        textView = view.findViewById(R.id.text_view_placeholder)

        textView.text = "No fragment to display"

        return view
    }
}

