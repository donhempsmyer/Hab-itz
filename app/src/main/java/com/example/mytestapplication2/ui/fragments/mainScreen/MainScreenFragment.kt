package com.example.mytestapplication2.ui.fragments.mainScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.mytestapplication2.databinding.FragmentMainScreenBinding
import com.example.mytestapplication2.R
import com.example.mytestapplication2.ui.fragments.currentDay.CurrentDayFragment
import com.example.mytestapplication2.ui.fragments.habitlist.HabitzScreenFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        // Finds the hamburger icon
        val hamburgerIcon: ImageView = binding.hamburgerIcon

        // Sets the click listener for the hamburger icon
        hamburgerIcon.setOnClickListener { view ->
            // Creates the popup menu
            val hamburgerPopupMenu = PopupMenu(requireContext(), hamburgerIcon)
            hamburgerPopupMenu.menuInflater.inflate(R.menu.hamburger_menu, hamburgerPopupMenu.menu)

            hamburgerPopupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {

                    R.id.habitz_navBtn -> {
                        // Navigate to the HabitzScreenFragment
                        findNavController().navigate(R.id.action_MainScreenFragment_to_HabitzScreenFragment)
                        true
                    }
                    R.id.journal_navBtn -> {
                        // Navigate to the Journal fragment
                        //findNavController().navigate(R.id.action_MainScreenFragment_to_JournalFragment)
                        true
                    }
                    R.id.scheduler_navBtn -> {
                        // Navigate to the CurrentDayFragment
                        findNavController().navigate(R.id.action_MainScreenFragment_to_CurrentDayFragment)
                        true
                    }
                    else -> false
                }
            }

            hamburgerPopupMenu.show()
        }

        return binding.root
    }

    // Function to replace the current fragment in the container
    //I(james) need a better understanding of this/ need more research
    private fun addFragmentToContainer(fragment: Fragment) {
        try {
            // Log to check if the function is being called
            //error checking via logcat
            Log.d("MainScreenFragment", "Replacing fragment with ${fragment::class.java.simpleName}")

            // Check if the fragment container exists
            val fragmentContainer = view?.findViewById<View>(R.id.fragment_container)
            if (fragmentContainer == null) {
                //error checking
                Log.e("MainScreenFragment", "Fragment container is null!")
                return
            }

            parentFragmentManager.beginTransaction()  // Starts the transaction
                .replace(R.id.fragment_container, fragment)  // Replaces the fragment
                .addToBackStack(null)  //Adds the transaction to back stack
                .commit()  // Commit the transaction to apply the changes
        } catch (e: Exception) {
            // Logs any errors that occur during the fragment transaction
            Log.e("MainScreenFragment", "Error during fragment transaction: ${e.localizedMessage}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}