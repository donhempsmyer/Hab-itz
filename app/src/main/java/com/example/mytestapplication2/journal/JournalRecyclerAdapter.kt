package com.example.mytestapplication2.journal

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mytestapplication2.databinding.FragmentJournalItemBinding

class JournalRecyclerAdapter(private val journalItemList : ArrayList<JournalItemData>) : RecyclerView.Adapter<JournalRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val binding: FragmentJournalItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = FragmentJournalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentJournalItem = journalItemList[position]

        holder.binding.journalItemImage.setImageResource(currentJournalItem.journalImage)
        holder.binding.journalItemTitle.text = currentJournalItem.journalTitle
        holder.binding.journalItemDate.text = currentJournalItem.journalDate
    }

    override fun getItemCount(): Int {
        return journalItemList.size
    }
}