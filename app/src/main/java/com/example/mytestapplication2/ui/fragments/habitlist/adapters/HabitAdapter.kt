package com.example.mytestapplication2.ui.fragments.habitlist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication2.R
import com.example.mytestapplication2.data.models.Habit
import com.example.mytestapplication2.ui.fragments.habitlist.HabitzScreenFragmentDirections
import com.example.mytestapplication2.utils.Calculations

class HabitAdapter : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    var habitList = emptyList<Habit>()
    val TAG = "HabitListAdapter"

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardView: CardView = itemView.findViewById(R.id.cvCardView)
        val titleTextView: TextView = itemView.findViewById(R.id.tvHabitTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tvHabitDescription)
        val timeElapsedTextView: TextView = itemView.findViewById(R.id.tvTimeElapsed)
        val timeStampTextView: TextView = itemView.findViewById(R.id.tvItemCreatedTimeStamp)

        init {
            cardView.setOnClickListener {
                val position = adapterPosition
                Log.d(TAG, "Item clicked at position $position")
                Log.d(TAG, "Item clicked at position ${habitList[position]}")

                val action = HabitzScreenFragmentDirections.actionHabitzScreenFragmentToUpdateHabitScreen(habitList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_habit_item, parent, false)
        return HabitViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    override fun onBindViewHolder(holder: HabitAdapter.HabitViewHolder, position: Int) {
        val currentHabit = habitList[position]
        holder.titleTextView.text = currentHabit.habitTitle
        holder.descriptionTextView.text = currentHabit.habitDescription
        holder.timeElapsedTextView.text = Calculations.calculateTimeBetweenDates(currentHabit.habitStartTime)
        holder.timeStampTextView.text = "Since: ${currentHabit.habitStartTime}"
    }

    fun setData(habit: List<Habit>) {
        this.habitList = habit
        notifyDataSetChanged()
    }
}

