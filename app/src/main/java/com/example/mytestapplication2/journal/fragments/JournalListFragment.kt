package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentJournalRecyclerBinding
import com.example.mytestapplication2.journal.viewModels.JournalViewModel

class JournalListFragment : Fragment() {

    private var _binding: FragmentJournalRecyclerBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val journalViewModel: JournalViewModel by activityViewModels()
    private lateinit var journalAdapter: JournalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJournalRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        if (journalViewModel.getJournalItemsById.value.isNullOrEmpty()) {
            binding.journalRecyclerEmptyText.visibility = View.VISIBLE
        } else {
            binding.journalRecyclerEmptyText.visibility = View.GONE
        }

        // Set up the adapter
        journalAdapter = JournalAdapter()

        // Set up the RecyclerView
        val recyclerView: RecyclerView = binding.journalRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.setHasFixedSize(true)

        // Connect the adapter to the RecyclerView
        recyclerView.adapter = journalAdapter

        // Pull data from the ViewModel, observe changes, and update the adapter
        journalViewModel.getJournalItemsById.observe(
            viewLifecycleOwner
        ) { journalItemList ->
            journalAdapter.submitList(journalItemList)
        }

        // Add button functionality
        binding.journalRecyclerAddBtn.setOnClickListener {
            findNavController().navigate(R.id.action_JournalListFragment_to_AddJournalItemFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}