package com.example.mytestapplication2.journal

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import com.example.mytestapplication2.R

import com.example.mytestapplication2.journal.placeholder.PlaceholderContent.PlaceholderItem
import com.example.mytestapplication2.databinding.FragmentJournalItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class JournalRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<JournalRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentJournalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.imageView.setImageResource(currentItem.)
        holder.titleView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentJournalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //val imageView: ImageView = binding.journalItemImage
        val titleView: TextView = binding.journalItemTitle

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }

}