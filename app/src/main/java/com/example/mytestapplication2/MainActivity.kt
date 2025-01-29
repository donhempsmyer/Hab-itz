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
        // Scheduler Button
        binding.bottomAppBar.findViewById<Button>(R.id.scheduler_navBtn).setOnClickListener {
            // Define possible destination fragments
            val destinations = listOf(R.id.CurrentDayFragment, R.id.HabitzScreenFragment, R.id.journal_fragment)

            // Get current destination
            val currentDestination = navController.currentDestination?.id

            // Find a new destination that is not the current one
            val destinationToNavigate = destinations.firstOrNull { it != currentDestination }

            if(currentDestination == R.id.CurrentDayFragment) {
                return@setOnClickListener
            }
            else{
                // If there's a destination available, navigate to it
                destinationToNavigate?.let {
                    navController.navigate(it)
                }
            }
        }

        // Home Button
        binding.bottomAppBar.findViewById<Button>(R.id.home_navBtn).setOnClickListener {
            // Define possible destination fragments
            val destinations = listOf(R.id.MainScreenFragment, R.id.HabitzScreenFragment, R.id.journal_fragment)

            // Get current destination
            val currentDestination = navController.currentDestination?.id

            // Find a new destination that is not the current one
            val destinationToNavigate = destinations.firstOrNull { it != currentDestination }

            if(currentDestination == R.id.MainScreenFragment) {
                return@setOnClickListener
            }
            else{
                // If there's a destination available, navigate to it
                destinationToNavigate?.let {
                    navController.navigate(it)
                }
            }
        }

        // Habitz Button
        binding.bottomAppBar.findViewById<Button>(R.id.habitz_navBtn).setOnClickListener {
            // Define possible destination fragments
            val destinations = listOf(R.id.HabitzScreenFragment, R.id.CurrentDayFragment, R.id.journal_fragment)

            // Get current destination
            val currentDestination = navController.currentDestination?.id

            // Find a new destination that is not the current one
            val destinationToNavigate = destinations.firstOrNull { it != currentDestination }

            if(currentDestination == R.id.HabitzScreenFragment) {
                return@setOnClickListener
            }
            else{
                // If there's a destination available, navigate to it
                destinationToNavigate?.let {
                    navController.navigate(it)
                }
            }
        }

        // Journal Button
        binding.bottomAppBar.findViewById<Button>(R.id.journal_navBtn).setOnClickListener {
            // Define possible destination fragments
            val destinations = listOf(R.id.journal_fragment, R.id.HabitzScreenFragment, R.id.MainScreenFragment)

            // Get current destination
            val currentDestination = navController.currentDestination?.id

            // Find a new destination that is not the current one
            val destinationToNavigate = destinations.firstOrNull { it != currentDestination }

            // Checks to see if the fragment you are on is equal to the id of the fragment to the button you click
            // Is a guard for clicking on the button for the same fragment your on
            if(currentDestination == R.id.journal_fragment) {
                return@setOnClickListener
            }
            else{
                // If there's a destination available, navigate to it
                destinationToNavigate?.let {
                    navController.navigate(it)
                }
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
