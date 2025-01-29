package com.example.mytestapplication2.ui.fragments.mainScreen

import Quotes
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mytestapplication2.R
import com.example.mytestapplication2.databinding.FragmentMainScreenBinding
import com.example.mytestapplication2.journal.fragments.JournalFragment
import com.example.mytestapplication2.ui.fragments.createhabit.CreateHabitScreen
import com.example.mytestapplication2.ui.fragments.currentDay.CurrentDayFragment
import com.example.mytestapplication2.ui.fragments.habitlist.HabitzScreenFragment
import kotlinx.serialization.json.Json
import java.io.File
import java.sql.Types.NULL

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val fragmentList = mutableListOf<Fragment>()
    private var currentFragmentIndex = 0

//    private lateinit var changePreviewBtn: ChangePreviewBtn

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        //val fragmentContainer = binding.fragmentContainer
        val btnToggleRight: Button = binding.btnToggleRight
        val btnToggleLeft: Button = binding.btnToggleLeft

        // Initialize the fragment list with default fragment if empty
        if (fragmentList.isEmpty()) {
            fragmentList.add(CurrentDayFragment())
            binding.previewPanelBtn.text = getString(R.string.schedule_preview_text)
        }

        // Replace the current fragment
        replaceFragment(fragmentList[currentFragmentIndex])

//        // Instantiate ChangePreviewBtn
//        changePreviewBtn = ChangePreviewBtn(requireContext(), binding, fragmentList)

        // Button listeners to toggle fragments
        btnToggleRight.setOnClickListener {

            val currentDayFragment = fragmentList.find {it is CurrentDayFragment}
            val currentJournalFragment = fragmentList.find {it is JournalFragment}
            val currentHabitFragment = fragmentList.find { it is HabitzScreenFragment }
            var process = true


            if(currentDayFragment != null) {
                binding.previewPanelBtn.text = getString(R.string.schedule_preview_text)
                process = false
            }
            if(currentJournalFragment != null) {
                binding.previewPanelBtn.text = getString(R.string.journal_preview_text)
                 process = false
            }
            if(currentHabitFragment != null) {
                binding.previewPanelBtn.text = getString(R.string.habits_preview_text)
                process = false
            }
            if(process == true){
                if(fragmentList.isEmpty()) {
                    binding.previewPanelBtn.text = getString(R.string.preview_panel)
                }
            }



//            // Calls the updater for the PreviewBtn that is in the ChangePreviewBtn Class
//            changePreviewBtn.updatePreviewBtn()

            if(fragmentList.size > 0) {
                currentFragmentIndex = (currentFragmentIndex + 1) % fragmentList.size
                replaceFragment(fragmentList[currentFragmentIndex])
            }

        }

        btnToggleLeft.setOnClickListener {
            currentFragmentIndex = if (currentFragmentIndex - 1 < 0) {
                fragmentList.size - 1
            } else {
                currentFragmentIndex - 1
            }
            replaceFragment(fragmentList[currentFragmentIndex])
        }

        // Load and display a random quote
        loadQuoteFromFile(requireContext(), binding.quoteText)

        // Preview Panel button functionality
        binding.previewPanelBtn.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.previewPanelBtn).apply {
                menuInflater.inflate(R.menu.customize_preview_panel_menu, menu)
                setOnMenuItemClickListener { item ->
                   if(item.itemId == NULL) {
                       //binding.previewPanelBtn.text = getString(R.string.preview_panel)
                   }
                    else {
                       when (item.itemId) {
                           R.id.menu_item_1 -> {
                               toggleFragment(CurrentDayFragment())
                               //binding.previewPanelBtn.text = getString(R.string.schedule_preview_text)

                           }

                           R.id.menu_item_2 -> {
                               toggleFragment(JournalFragment())
                               //binding.previewPanelBtn.text = getString(R.string.journal_preview_text)

                           }

                           R.id.menu_item_3 -> {
                               toggleFragment(HabitzScreenFragment())
                               //binding.previewPanelBtn.text = getString(R.string.habits_preview_text)

                           }

                           else -> false
                       }
                   }
                    true
                }
            }
            popupMenu.show()
        }

//        // A recall to update the preview panel W/O click on the toggleBtn this is used to hit the null statement
//        // and change text to previewPanel when panel is empty
//        changePreviewBtn.updatePreviewBtn()

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragmentList.isEmpty()) {
            val textView = TextView(context)
            textView.text = "No fragment to display"
            textView.gravity = Gravity.CENTER
            textView.textSize = 20f

            childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PlaceholderFragment())
                .commit()
        }
        else{
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    private fun loadQuoteFromFile(context: Context, textView: TextView) {
        val file = File(context.filesDir, "quotes.json")

        // Log the file path to check
        Log.d("FilePath", "File path: ${file.absolutePath}")

        if (file.exists()) {
            try {
                val jsonString = file.readText()
                val quotesList = Json.decodeFromString<List<Quotes>>(jsonString)
                val randomQuote = quotesList.random()
                textView.text = "\"${randomQuote.quote}\"\n\n- ${randomQuote.author}"
            } catch (e: Exception) {
                Log.e("QuoteError", "Error reading or parsing quotes", e)
                textView.text = "Error loading quotes."
            }
        } else {
            textView.text = "No quotes available."
        }
    }

    private fun toggleFragment(fragment: Fragment) {
        val existingFragment = fragmentList.find { it::class == fragment::class }
        if (existingFragment != null) {
            fragmentList.remove(existingFragment)
        } else {
            fragmentList.add(fragment)
        }

        if (fragmentList.isNotEmpty()) {
            replaceFragment(fragmentList[currentFragmentIndex])
        } else {
            replaceFragment(CurrentDayFragment())
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
