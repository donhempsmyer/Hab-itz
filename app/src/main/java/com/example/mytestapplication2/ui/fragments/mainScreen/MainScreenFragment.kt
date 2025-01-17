package com.example.mytestapplication2.ui.fragments.mainScreen

import Quotes
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentMainScreenBinding
import com.example.mytestapplication2.journal.fragments.JournalFragment
import com.example.mytestapplication2.ui.fragments.currentDay.CurrentDayFragment
import com.example.mytestapplication2.ui.fragments.habitlist.HabitzScreenFragment
import kotlinx.serialization.json.Json
import java.io.File

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    // Fragment list for preview screen with dynamically toggling
    private val fragmentList = listOf(
        CurrentDayFragment(),
        JournalFragment(),
        HabitzScreenFragment()
    )

    private var currentFragmentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        val fragmentContainer = binding.fragmentContainer
        val btnToggleRight: Button = binding.btnToggleRight
        val btnToggleLeft: Button = binding.btnToggleLeft


        // Initialize the first fragment
        replaceFragment(fragmentList[currentFragmentIndex])

        // Set the btnToggleRight button listener
        btnToggleRight.setOnClickListener {
            // Move to the next fragment in the list (loop back if we reach the end)
            currentFragmentIndex = (currentFragmentIndex + 1) % fragmentList.size
            replaceFragment(fragmentList[currentFragmentIndex])
        }

        // Set the btnToggleLeft button listener
        btnToggleLeft.setOnClickListener {
            // Move to the previous fragment in the list (loop back if we reach the start)
            currentFragmentIndex = if (currentFragmentIndex - 1 < 0) {
                fragmentList.size - 1
            } else {
                currentFragmentIndex - 1
            }
            replaceFragment(fragmentList[currentFragmentIndex])
        }



        //Calls func to load and display a random quote
        loadQuoteFromFile(requireContext(), binding.quoteText)

        return binding.root
    }

    // function to replace the current fragment in the container
    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun loadQuoteFromFile(context: Context, textView: TextView) {

            // Get the file from internal storage
            val file = File(context.filesDir, "quotes.json")

            // Log the file path to check
            Log.d("FilePath", "File path: ${file.absolutePath}")

            // Check if the file exists
            if (file.exists()) {
                // Read the JSON content from the file
                val jsonString = file.readText()

                // Deserialize the JSON into a list of Quotes
                val quotesList = Json.decodeFromString<List<Quotes>>(jsonString)

                // Get a random quote
                val randomQuote = quotesList.random()

                // Set the random quote to the TextView
                textView.text = "\"${randomQuote.quote}\"\n\n- ${randomQuote.author}"
            } else {
                textView.text = "No quotes available."
            }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
