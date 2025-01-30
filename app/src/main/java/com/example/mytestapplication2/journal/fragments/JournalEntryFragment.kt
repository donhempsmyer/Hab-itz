package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
//import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentJournalEntryBinding
import com.example.mytestapplication2.journal.data.JournalItem
import com.example.mytestapplication2.journal.viewModels.JournalViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class JournalEntryFragment : Fragment() {

    private var _binding: FragmentJournalEntryBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val journalViewModel: JournalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJournalEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        //binding.journalEntryTitleEditText.setText(listViewModel.journalEntryTitle.value)

        // Save button functionality
        binding.entrySaveBtn.setOnClickListener {
            addJournalItemToDatabase()

            // Update the ViewModel with the new title and body
            //listViewModel.setJournalEntryTitle(savedTitle)
            //listViewModel.setJournalEntryBody(savedBody)

        }

        // Cancel button functionality
        binding.entryCancelBtn.setOnClickListener {

            findNavController().navigate(R.id.action_JournalEntryFragment_to_JournalListFragment)

        }
    }

    private fun addJournalItemToDatabase() {

        // Get the input data from the EditText fields and the current date
        val title = binding.entryTitleText.text.toString()
        val body = binding.entryBodyText.text.toString()
        val date = SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault()).format(System.currentTimeMillis())

        if (title.isNotEmpty() && body.isNotEmpty()) {

            // Create a new JournalItem using the input data
            val item = JournalItem(title, date, body)

            // Add the new JournalItem to the database using the ViewModel
            journalViewModel.upsertJournalItem(item)

            val snackbar = Snackbar.make(binding.root, "Habit created successfully!", Snackbar.LENGTH_SHORT)

            findNavController().navigate(R.id.action_JournalEntryFragment_to_JournalListFragment)
        } else {


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}