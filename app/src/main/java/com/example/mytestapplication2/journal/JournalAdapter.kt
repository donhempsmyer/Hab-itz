package com.example.mytestapplication2.journal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mytestapplication2.databinding.FragmentJournalItemBinding
import com.example.mytestapplication2.journal.data.JournalItem

class JournalAdapter(
    private val itemClickListener: (JournalItem) -> Unit
) : ListAdapter<JournalItem, JournalAdapter.JournalViewHolder>(JournalItemDiffCallback) {

    object JournalItemDiffCallback : DiffUtil.ItemCallback<JournalItem>() {

        override fun areItemsTheSame(
            oldJournalItem: JournalItem,
            newJournalItem: JournalItem
        ): Boolean {

            return oldJournalItem.journalId == newJournalItem.journalId
        }

        override fun areContentsTheSame(
            oldJournalItem: JournalItem,
            newJournalItem: JournalItem
        ): Boolean {

            return oldJournalItem == newJournalItem
        }
    }

    // Describes an item view and its place within the RecyclerView
    inner class JournalViewHolder(
        val binding: FragmentJournalItemBinding,
        val itemClickListener: (JournalItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {

                val position = bindingAdapterPosition

                if (position != RecyclerView.NO_POSITION)
                    itemClickListener(getItem(position))
            }
        }

        fun bind(journalItemData: JournalItem) {
            binding.journalItemIconImageView.setImageResource(journalItemData.journalImage)
            binding.journalItemTitleTextView.text = journalItemData.journalTitle
            binding.journalItemDateTextView.text = journalItemData.journalDate
        }
    }

    // Returns a new JournalViewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JournalViewHolder {

        val binding = FragmentJournalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JournalViewHolder(binding, itemClickListener)
    }

    // Displays journal data at a certain position
    override fun onBindViewHolder(
        holder: JournalViewHolder,
        position: Int
    ) {

        holder.bind(getItem(position))
    }
}