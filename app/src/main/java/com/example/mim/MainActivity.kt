package com.example.mim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android.navigationadvancedsample.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navGraphIds = listOf(
                R.navigation.home_nav,
                R.navigation.menu_nav,
                R.navigation.order_buy_nav,
                R.navigation.profile_nav
        )

        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_container,
                intent = intent
        )

//        Whenever the selected controller changes, setup the action bar.
//        controller.observe(this, Observer { navController ->
//            setupActionBarWithNavController(navController)
//        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}