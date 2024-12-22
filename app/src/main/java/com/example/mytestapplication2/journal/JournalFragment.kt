package com.example.mytestapplication2.journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapplication2.R
import com.example.mytestapplication2.journal.placeholder.PlaceholderContent
import com.example.mytestapplication2.journal.JournalData

/**
 * A fragment representing a list of Items.
 */
class JournalFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var JournalDataList: ArrayList<JournalData>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageList = arrayOf(
            R.drawable.book_icon
        )

        titleList = arrayOf(
            "Journal Entry"
        )

        //recyclerView = findViewById(R.id.recyclerView)
        //recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        JournalDataList = arrayListOf<JournalData>()
        getData()

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                recyclerView.adapter = JournalRecyclerViewAdapter(JournalDataList)
            }
        }
        return view
    }

    private fun getData() {
        for (i in imageList.indices) {
            val journalData = JournalData(imageList[i], titleList[i])
            JournalDataList.add(journalData)
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            JournalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}