package com.example.mytestapplication2.ui.fragments.mainScreen

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentMainScreenBinding
import com.example.mytestapplication2.journal.fragments.JournalFragment
import com.example.mytestapplication2.ui.fragments.currentDay.CurrentDayFragment
import com.example.mytestapplication2.ui.fragments.habitlist.HabitzScreenFragment
import java.sql.Types.NULL

class ChangePreviewBtn(val context: Context, val binding:FragmentMainScreenBinding, fragmentList: MutableList<Fragment>)  {

    val currentDayFragment = fragmentList.find {it is CurrentDayFragment }
    val currentJournalFragment = fragmentList.find {it is JournalFragment }
    val currentHabitFragment = fragmentList.find { it is HabitzScreenFragment }
    var process = true

    private var currentFragmentIndex = -1
    private val previewTexts = listOf(
        context.getString(R.string.schedule_preview_text),
        context.getString(R.string.journal_preview_text),
        context.getString(R.string.habits_preview_text),
        context.getString(R.string.preview_panel)
    )
    private var currentTextIndex = 0

   fun updatePreviewBtn() {
       // Cycle through the preview texts based on the current fragment
       when {
           currentDayFragment != null -> {
               binding.previewPanelBtn.text = previewTexts[0]
               currentTextIndex = (currentTextIndex + 1) % previewTexts.size
           }
           currentJournalFragment != null -> {
               binding.previewPanelBtn.text = previewTexts[1]
               currentTextIndex = (currentTextIndex + 1) % previewTexts.size
           }
           currentHabitFragment != null -> {
               binding.previewPanelBtn.text = previewTexts[2]
               currentTextIndex = (currentTextIndex + 1) % previewTexts.size
           }
           else -> {
               binding.previewPanelBtn.text = previewTexts[3]
           }
       }
    }
}