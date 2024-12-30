package com.example.mytestapplication2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mytestapplication2.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // assigning id of the toolbar to a var
        val mainToolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        //using toolbar as  ActionBar
        setSupportActionBar(mainToolbar)

        // Set the hamburger icon as the navigation icon
        binding.toolbar.setNavigationIcon(R.drawable.hamburgericon)

//        //doesn't work(should work)
//        // Sets the nav Icon to the hamburger_icon
//        mainToolbar.setNavigationIcon(R.drawable.hamburger_icon)

//        //doesn't work lol
//        // Disable the default back arrow if needed (no need if using hamburger icon)
//        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Handle the click on the hamburger icon
        binding.toolbar.setNavigationOnClickListener { view ->
            val hamburgerPopupMenu = PopupMenu(this, view)
            hamburgerPopupMenu.menuInflater.inflate(R.menu.hamburger_menu, hamburgerPopupMenu.menu)

            hamburgerPopupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.home_navBtn -> {
                        //Navigate to the MainScreenFragment
                        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_CurrentDayFragment_to_MainScreenFragment)
                        true
                    }
                    R.id.habitz_navBtn -> {
                        // Navigate to the HabitzScreenFragment
                        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_MainScreenFragment_to_HabitzScreenFragment)
                        true
                    }
                    R.id.journal_navBtn -> {
                        // Navigate to the Journal fragment
                       // findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_MainScreenFragment_to_JournalFragment)
                        true
                    }
                    R.id.scheduler_navBtn -> {
                        // Navigate to the CurrentDayFragment
                        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_MainScreenFragment_to_CurrentDayFragment)
                        true
                    }
                    else -> false
                }
            }

            hamburgerPopupMenu.show()
        }
    }

}
