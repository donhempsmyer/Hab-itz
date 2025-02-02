package com.example.mytestapplication2.ui.fragments.mainScreen

import Quotes
import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.mytestapplication2.ui.fragments.currentDay.CurrentDayFragment
import com.example.mytestapplication2.ui.fragments.habitlist.HabitzScreenFragment
import kotlinx.serialization.json.Json
import java.io.File

class MainScreenFragment : Fragment() {

    // All Variables
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    val fragmentList = mutableListOf<Fragment>()
    var currentFragmentIndex = 0

    private lateinit var changePreviewBtn: ChangePreviewBtn
    private lateinit var fragmentToggleManager: FragmentToggleManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)



//        // Initialize the fragment list with default fragment if empty
//        if (fragmentList.isEmpty()) {
//            fragmentList.add(CurrentDayFragment())
//        }

        // Initialize preview texts
        val previewTexts = listOf(
            getString(R.string.schedule_preview_text),
            getString(R.string.journal_preview_text),
            getString(R.string.habits_preview_text),
            getString(R.string.preview_panel)
        )

        // Instantiate ChangePreviewBtn
        changePreviewBtn = ChangePreviewBtn(requireContext(), binding, previewTexts)

        // Pass the changePreviewBtn instance when initializing FragmentToggleManager
        fragmentToggleManager = FragmentToggleManager(fragmentList, childFragmentManager, R.id.fragment_container, changePreviewBtn)

        val btnToggleRight: Button = binding.btnToggleRight
        val btnToggleLeft: Button = binding.btnToggleLeft

        // Update the preview button immediately when the fragment is loaded
        changePreviewBtn.updatePreviewBtn(currentFragmentIndex, fragmentList)

        // Button listeners to toggle fragments
        btnToggleRight.setOnClickListener {
            if (fragmentList.isNotEmpty()) {
                currentFragmentIndex = (currentFragmentIndex + 1) % fragmentList.size
                fragmentToggleManager.replaceFragment(fragmentList[currentFragmentIndex])
                changePreviewBtn.updatePreviewBtn(currentFragmentIndex, fragmentList)
            }
        }

        btnToggleLeft.setOnClickListener {
            if (fragmentList.isNotEmpty()) {
                currentFragmentIndex = if (currentFragmentIndex - 1 < 0) {
                    fragmentList.size - 1
                } else {
                    currentFragmentIndex - 1
                }
                fragmentToggleManager.replaceFragment(fragmentList[currentFragmentIndex])
                changePreviewBtn.updatePreviewBtn(currentFragmentIndex, fragmentList)
            }
        }

        // Load and display a random quote
        loadQuoteFromFile(requireContext(), binding.quoteText)

        // Preview Panel button functionality
        binding.previewPanelBtn.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.previewPanelBtn).apply {
                menuInflater.inflate(R.menu.customize_preview_panel_menu, menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_item_1 -> {
                            fragmentToggleManager.toggleFragment(CurrentDayFragment())
                        }
                        R.id.menu_item_2 -> {
                            fragmentToggleManager.toggleFragment(JournalFragment())
                        }
                        R.id.menu_item_3 -> {
                            fragmentToggleManager.toggleFragment(HabitzScreenFragment())
                        }
                        else -> false
                    }
                    true
                }
            }
            popupMenu.show()
        }

        // Update the preview button immediately when the fragment is loaded
        changePreviewBtn.updatePreviewBtn(currentFragmentIndex, fragmentList)

        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

