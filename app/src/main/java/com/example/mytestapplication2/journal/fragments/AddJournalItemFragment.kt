package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentAddJournalItemBinding
import com.example.mytestapplication2.journal.data.JournalItem
import com.example.mytestapplication2.journal.viewModels.JournalViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class AddJournalItemFragment : Fragment() {

    private var _binding: FragmentAddJournalItemBinding? = null
    private val binding get() = _binding!!

    private val journalViewModel: JournalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddJournalItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val navToList = R.id.action_AddJournalItemFragment_to_JournalListFragment

        // Save button functionality
        binding.addItemSaveBtn.setOnClickListener {
            addJournalItemToDB()
            findNavController().navigate(navToList)
        }

        // Cancel button functionality
        binding.addItemCancelBtn.setOnClickListener {
            findNavController().navigate(navToList)
        }
    }

    private fun addJournalItemToDB() {

        // Get the input data from the EditText fields and the current date
        var title: String = binding.addItemTitleText.text.toString()
        val body = binding.addItemContentText.text.toString()
        val date = SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault()).format(System.currentTimeMillis())

        if (title.isEmpty()) {
            title = "Untitled"
        }

        // Create a new JournalItem using the input data
        val item = JournalItem(
            itemTitle = title,
            itemDate = date,
            itemContent = body,
            itemImage = R.drawable.book_icon
        )

        // Add the new JournalItem to the database using the ViewModel
        journalViewModel.upsertJournalItem(item)

        val snackbar = Snackbar.make(binding.root, "Journal entry created successfully!", Snackbar.LENGTH_SHORT)
        val snackbarView = snackbar.view
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.habits_blue))
        snackbar.setTextColor(ContextCompat.getColor(requireContext(), R.color.habits_green))

        val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(params.leftMargin, params.topMargin + 200, params.rightMargin, params.bottomMargin + 200)
        snackbarView.layoutParams = params

        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}