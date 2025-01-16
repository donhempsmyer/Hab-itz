package com.example.mytestapplication2.ui.fragments.currentDay

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication2.data.models.TimeCell
import com.example.mytestapplication2.databinding.TimeCardviewBinding


class TimeAdapter (
    val timeList: List<TimeCell>,
    var userTextList: ArrayList<String> = ArrayList<String>(48),


) : RecyclerView.Adapter<CardViewHolder>() {

    lateinit var addEventButton: ImageButton
    //lateinit var editedText: String

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TimeCardviewBinding.inflate(from, parent,false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        addEventButton = holder.bindAddButton()
        //editedText = holder.bindEditText()

        holder.bindTimeCell(timeList[position])

        addEventButton.setOnClickListener{

            holder.replaceUserText(position)
        }


    }

    override fun getItemCount(): Int = timeList.size

    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    //fun interface OnClickListener {
     //   fun onClick(position: Int, model: ImageButton)
    //}


}

