package com.example.mytestapplication2.ui.fragments.mainScreen

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentMainScreenBinding
import com.example.mytestapplication2.journal.fragments.JournalFragment
import com.example.mytestapplication2.ui.fragments.currentDay.CurrentDayFragment
import com.example.mytestapplication2.ui.fragments.habitlist.HabitzScreenFragment
import java.sql.Types.NULL

class ChangePreviewBtn(
    val context: Context,
    val binding: FragmentMainScreenBinding,
    val previewTexts: List<String>
) {

    fun updatePreviewBtn(currentFragmentIndex: Int, fragmentList: MutableList<Fragment>) {
        val currentFragment = fragmentList.getOrNull(currentFragmentIndex)

        // Update the button text based on the active fragment
        when (currentFragment) {
            is CurrentDayFragment -> binding.previewPanelBtn.text = previewTexts[0]
            is JournalFragment -> binding.previewPanelBtn.text = previewTexts[1]
            is HabitzScreenFragment -> binding.previewPanelBtn.text = previewTexts[2]
            is PlaceholderFragment -> binding.previewPanelBtn.text = previewTexts[3]
        }
    }
}



