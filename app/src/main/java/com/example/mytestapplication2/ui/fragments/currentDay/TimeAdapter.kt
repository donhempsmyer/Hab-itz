package com.example.mytestapplication2.ui.fragments.currentDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication2.data.models.TimeCell
import com.example.mytestapplication2.databinding.TimeCardviewBinding


class TimeAdapter (
    val timeList: List<TimeCell>,

) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TimeCardviewBinding.inflate(from, parent,false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindTimeCell(timeList[position])
    }

    override fun getItemCount(): Int = timeList.size
}
