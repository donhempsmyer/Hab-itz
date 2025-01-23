package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapplication2.databinding.FragmentJournalEntryBinding
import com.example.mytestapplication2.journal.data.JournalItem


class JournalEntryFragment : Fragment() {

    private var _binding: FragmentJournalEntryBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

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

        // Save button functionality
        binding.journalEntrySaveButton.setOnClickListener {
            val journalEntryTitle = binding.journalEntryTitleEditText.text.toString()
            val journalEntryContent = binding.journalEntryBodyEditText.text.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}