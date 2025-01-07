package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentJournalRecyclerBinding
import com.example.mytestapplication2.journal.JournalItemData
import com.example.mytestapplication2.journal.JournalRecyclerAdapter

class JournalFragment : Fragment() {

    private var _binding: FragmentJournalRecyclerBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    //private lateinit var recyclerView: RecyclerView
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalRecyclerBinding.inflate(inflater, container, false)
        val view = binding.root

        val journalItemList = ArrayList<JournalItemData>()

        journalItemList.add(JournalItemData(R.drawable.book_icon, "Journal Entry 1", "Today"))
        journalItemList.add(JournalItemData(R.drawable.book_icon, "Journal Entry 2", "12-22-2024"))
        journalItemList.add(JournalItemData(R.drawable.book_icon, "Journal Entry 3", "12-20-2024"))

        imageList = arrayOf(
            R.drawable.book_icon
        )

        titleList = arrayOf(
            "Journal Entry"
        )

        val recyclerView = binding.journalRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Set the adapter
        recyclerView.adapter = JournalRecyclerAdapter(journalItemList)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}