package com.example.mytestapplication2.journal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentUpdateJournalItemBinding
import com.example.mytestapplication2.journal.data.JournalItem
import com.example.mytestapplication2.journal.viewModels.JournalViewModel
import com.google.android.material.snackbar.Snackbar

class UpdateJournalItemFragment : Fragment() {

    private var _binding: FragmentUpdateJournalItemBinding? = null
    private val binding get() = _binding!!

    private val journalViewModel: JournalViewModel by activityViewModels()
    private val args by navArgs<UpdateJournalItemFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateJournalItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateItemTitleText.setText(args.selectedJournalItem.itemTitle)
        binding.updateItemDate.text = args.selectedJournalItem.itemDate
        binding.updateItemContentText.setText(args.selectedJournalItem.itemContent)

        binding.updateItemSaveBtn.setOnClickListener {
            updateJournalItemInDB()
        }

        binding.updateItemCancelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_updateJournalItemFragment_to_JournalListFragment)
        }

        binding.updateItemDeleteBtn.setOnClickListener {
            deleteJournalItemFromDB()
        }
    }

    private fun updateJournalItemInDB() {
        // Get the input data from the EditText fields
        var title = binding.updateItemTitleText.text.toString()
        val body = binding.updateItemContentText.text.toString()

        if (title.isEmpty()) {
            title = "Untitled"
        }

        val updatedItem = JournalItem(
            itemTitle = title,
            itemDate = args.selectedJournalItem.itemDate,
            itemContent = body,
            itemImage = args.selectedJournalItem.itemImage,
            itemId = args.selectedJournalItem.itemId
        )

        journalViewModel.upsertJournalItem(updatedItem)

        findNavController().navigate(R.id.action_updateJournalItemFragment_to_JournalListFragment)

        val snackbar = Snackbar.make(binding.root, "Journal entry updated successfully!", Snackbar.LENGTH_SHORT)
        val snackbarView = snackbar.view
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.habits_blue))
        snackbar.setTextColor(ContextCompat.getColor(requireContext(), R.color.habits_green))

        val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(params.leftMargin, params.topMargin + 200, params.rightMargin, params.bottomMargin + 200)
        snackbarView.layoutParams = params

        snackbar.show()
    }

    private fun deleteJournalItemFromDB() {
        // Create an alert dialog to confirm the deletion
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Delete '${args.selectedJournalItem.itemTitle}'")
        builder.setMessage("Are you sure you want to delete this journal entry?")

        builder.setPositiveButton("Yes") { _, _ ->

            journalViewModel.deleteJournalItem(args.selectedJournalItem)

            findNavController().navigate(R.id.action_updateJournalItemFragment_to_JournalListFragment)

            val snackbar = Snackbar.make(binding.root, "Journal entry deleted successfully", Snackbar.LENGTH_SHORT)
            val snackbarView = snackbar.view
            snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.habits_blue))
            snackbar.setTextColor(ContextCompat.getColor(requireContext(), R.color.habits_green))

            val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(params.leftMargin, params.topMargin + 200, params.rightMargin, params.bottomMargin + 200)
            snackbarView.layoutParams = params

            snackbar.show()
        }

        builder.setNegativeButton("No") { _, _ -> }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}