package com.example.mytestapplication2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.mytestapplication2.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import saveQuotesToFile

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Sets the toolbar
        val bottomAppToolbar = findViewById<BottomAppBar>(R.id.bottomAppBar)

        //Sets the toolbar as ActionBar
        setSupportActionBar(bottomAppToolbar)

        //Sets the NavController to the nav_graph
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Set up OnClickListener for the BottomAppBar buttons
        binding.bottomAppBar.findViewById<Button>(R.id.scheduler_navBtn).setOnClickListener {
            // Only navigate if not already on the CurrentDayFragment
            val currentDestination = navController.currentDestination?.id
            if (currentDestination != R.id.CurrentDayFragment) {
                navController.navigate(R.id.action_MainScreenFragment_to_CurrentDayFragment)
            }
        }

        binding.bottomAppBar.findViewById<Button>(R.id.home_navBtn).setOnClickListener {
            // Only navigate if not already on the MainScreenFragment
            val currentDestination = navController.currentDestination?.id
            if (currentDestination != R.id.MainScreenFragment) {
                navController.navigate(R.id.action_CurrentDayFragment_to_MainScreenFragment)
            }
        }

        binding.bottomAppBar.findViewById<Button>(R.id.habitz_navBtn).setOnClickListener {
            // Only navigate if not already on the HabitzScreenFragment
            val currentDestination = navController.currentDestination?.id
            if (currentDestination != R.id.HabitzScreenFragment) {
                navController.navigate(R.id.action_MainScreenFragment_to_HabitzScreenFragment)
            }
        }
//        need to activate when we merge the journal fragment
        binding.bottomAppBar.findViewById<Button>(R.id.journal_navBtn).setOnClickListener {
            // Only navigate if not already on the JournalFragment (when it exists)
            val currentDestination = navController.currentDestination?.id
            if (currentDestination != R.id.journal_fragment) {
                navController.navigate(R.id.action_MainScreenFragment_to_JournalFragment)
            }
        }

        binding.bottomAppBar.findViewById<Button>(R.id.home_navBtn).setOnClickListener {
            // Only navigate if not already on the MainScreenFragment
            val currentDestination = navController.currentDestination?.id
            if (currentDestination != R.id.MainScreenFragment) {
                navController.navigate(R.id.action_journal_fragment_to_MainScreenFragment)
            }
        }

        //Calls func to save the quotes list
        try {
            saveQuotesToFile(applicationContext)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error saving quotes file: ${e.message}")
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}
