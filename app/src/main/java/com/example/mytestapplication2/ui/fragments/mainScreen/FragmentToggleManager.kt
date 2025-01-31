package com.example.mytestapplication2.ui.fragments.mainScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mytestapplication2.R
import com.example.mytestapplication2.ui.fragments.currentDay.CurrentDayFragment

class FragmentToggleManager(
    private val fragmentList: MutableList<Fragment>,
    private val childFragmentManager: FragmentManager,
    private val containerId: Int,
    private val changePreviewBtn: ChangePreviewBtn
) {

    fun toggleFragment(fragment: Fragment) {
        val existingFragment = fragmentList.find { it::class == fragment::class }

//        if (fragmentList.isEmpty()) {
//            fragmentList.add(CurrentDayFragment())
//            changePreviewBtn.updatePreviewBtn(fragmentList.indexOf(fragmentList.last()), fragmentList)
//        }

        if (existingFragment != null) {
            fragmentList.remove(existingFragment)
        } else {
            // Add the new fragment if it doesn't exist
            fragmentList.add(fragment)
        }

        // Remove the placeholder fragment if it's in the list
        fragmentList.removeAll { it is PlaceholderFragment }

        // Check if the fragment list is empty, if so add the placeholder
        if (fragmentList.isEmpty()) {
            val placeholderFragment = PlaceholderFragment()
            fragmentList.add(placeholderFragment)
        }

        // Replace the current fragment if there are any fragments in the list
        if (fragmentList.isNotEmpty()) {
            replaceFragment(fragmentList.last())
            // Updates the preview btn text
            changePreviewBtn.updatePreviewBtn(fragmentList.indexOf(fragmentList.last()), fragmentList)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}
