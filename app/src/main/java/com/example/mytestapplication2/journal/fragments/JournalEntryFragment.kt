package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
//import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentJournalEntryBinding
import com.example.mytestapplication2.journal.viewModels.JournalListViewModel

class JournalEntryFragment : Fragment() {

    private var _binding: FragmentJournalEntryBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val listViewModel: JournalListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJournalEntryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.journalEntryTitleEditText.setText(listViewModel.journalEntryTitle.value)

        // Save button functionality
        binding.journalEntrySaveButton.setOnClickListener {
            val savedTitle = binding.journalEntryTitleEditText.text.toString()
            val savedBody = binding.journalEntryBodyEditText.text.toString()

            // Update the ViewModel with the new title and body
            listViewModel.setJournalEntryTitle(savedTitle)
            listViewModel.setJournalEntryBody(savedBody)

            //val transaction = parentFragmentManager.beginTransaction()
            findNavController().navigate(R.id.action_JournalEntryFragment_to_JournalListFragment)
            //transaction.replace(R.id.fragment_container_main_screen, fragment)
            //transaction.addToBackStack(null)
            //transaction.commit()
        }

        // Cancel button functionality
        binding.journalEntryCancelButton.setOnClickListener {

            //val transaction = parentFragmentManager.beginTransaction()
            findNavController().navigate(R.id.action_JournalEntryFragment_to_JournalListFragment)
            //transaction.replace(R.id.fragment_container_main_screen, fragment)
            //transaction.addToBackStack(null)
            //transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}