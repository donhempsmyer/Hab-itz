package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentJournalRecyclerBinding
import com.example.mytestapplication2.journal.data.JournalItem
import com.example.mytestapplication2.journal.JournalAdapter
import com.example.mytestapplication2.journal.viewModels.JournalListViewModel

class JournalListFragment : Fragment() {

    private var _binding: FragmentJournalRecyclerBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val listViewModel: JournalListViewModel by activityViewModels()
    private lateinit var journalAdapter: JournalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalRecyclerBinding.inflate(inflater, container, false)
        val view = binding.root

        // Modify this list to pull data from user input (eventually a database)
        //val journalItemList = ArrayList<JournalItem>()

        //journalItemList.add(JournalItem(1, R.drawable.book_icon, "First Journal Entry", "01-01-2025"))

        //journalAdapter = JournalAdapter { journalItem: JournalItem ->
           // journalAdapterOnClick(journalItem)
        //}

        //journalAdapter.submitList(journalItemList)

        //val recyclerView: RecyclerView = binding.journalRecycler
        //recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //recyclerView.setHasFixedSize(true)

        // Set the adapter
        // Fix this to open JournalEntryFragment when an item is clicked
        //recyclerView.adapter = journalAdapter


        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the adapter
        journalAdapter = JournalAdapter { journalItem ->
            journalAdapterOnClick(journalItem)
        }

        // Set up the RecyclerView
        val recyclerView: RecyclerView = binding.journalRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Connect the adapter to the RecyclerView
        recyclerView.adapter = journalAdapter

        // Pull data from the ViewModel, observe changes, and update the adapter
        listViewModel.journalItemList.observe(
            viewLifecycleOwner,
            Observer { journalItemList ->
            journalAdapter.submitList(journalItemList)
            }
        )
    }

    private fun journalAdapterOnClick(journalItem: JournalItem) {
        //val fragment = JournalEntryFragment()
        val transaction = parentFragmentManager.beginTransaction()
        findNavController().navigate(R.id.action_JournalListFragment_to_JournalEntryFragment)
        //transaction.replace(R.id.fragment_container_main_screen, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}