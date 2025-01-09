package com.example.mytestapplication2.ui.fragments.currentDay

import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication2.data.models.TimeCell
import com.example.mytestapplication2.data.models.timeList
import com.example.mytestapplication2.databinding.TimeCardviewBinding

class CardViewHolder(
    private val timeCardviewBinding: TimeCardviewBinding

) : RecyclerView.ViewHolder(timeCardviewBinding.root) {

    fun bindTimeCell(timeCell: TimeCell) {
        timeCardviewBinding.tvTime.text = timeCell.time
        timeCardviewBinding.userText.setText(timeCell.timeContent)
        timeList.add(timeCell)
    }
}