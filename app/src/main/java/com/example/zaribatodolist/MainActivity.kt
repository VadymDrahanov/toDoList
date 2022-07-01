package com.example.zaribatodolist

import android.icu.text.DisplayContext
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.WindowInsets.Type.systemBars
import androidx.appcompat.widget.Toolbar
import androidx.core.view.*
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat.Type.systemBars
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.zaribatodolist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


//        setSupportActionBar(findViewById(R.id.toolbar))
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        val config = AppBarConfiguration(navController.graph)
//
//        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, config)
//
//        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
//            title = when (destination.id) {
//                R.id.holderFragment -> "Task List"
//                else -> "Default title"
//            }
//
//        }


    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.toolbar_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when(item.itemId){
//            R.id.goToSignOutButton -> {
//
//            }
//        }
//
//        return true
//    }
}