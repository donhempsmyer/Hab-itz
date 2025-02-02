package com.example.mytestapplication2.journal.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mytestapplication2.databinding.FragmentJournalItemBinding
import com.example.mytestapplication2.journal.data.JournalItem

class JournalAdapter : ListAdapter<JournalItem, JournalAdapter.JournalViewHolder>(JournalItemDiffCallback) {

    private var journalItemList: List<JournalItem> = emptyList()

    // Compares two lists and determines the differences between them
    object JournalItemDiffCallback : DiffUtil.ItemCallback<JournalItem>() {

        override fun areItemsTheSame(
            oldItem: JournalItem,
            newItem: JournalItem
        ): Boolean {

            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(
            oldItem: JournalItem,
            newItem: JournalItem
        ): Boolean {

            return oldItem == newItem
        }
    }

    // Describes an item view and its place within the RecyclerView
    inner class JournalViewHolder(
        val binding: FragmentJournalItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {

                val position = bindingAdapterPosition
                val selectedItem: JournalItem = getItem(position)
                val action = JournalListFragmentDirections.actionJournalListFragmentToUpdateJournalItemFragment(selectedItem)

                binding.root.findNavController().navigate(action)
            }
        }

        fun bind(listItem: JournalItem) {
            binding.journalItemTitle.text = listItem.itemTitle
            binding.journalItemDate.text = listItem.itemDate
            listItem.itemImage?.let { binding.journalItemIcon.setImageResource(it) }
        }
    }

    // Returns a new JournalViewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JournalViewHolder {

        val binding = FragmentJournalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JournalViewHolder(binding)
    }

    // Displays journal data at a certain position
    override fun onBindViewHolder(
        holder: JournalViewHolder,
        position: Int
    ) {

        holder.bind(getItem(position))
    }
}