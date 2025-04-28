package com.example.carlile_finalprojectpart1.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.carlile_finalprojectpart1.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.HomeFragment,
                R.id.BrowseFragment,
                R.id.SearchFragment,
                R.id.FavoritesFragment),
            drawerLayout
        )

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.action_home_to_browse)
                    navController.navigate(R.id.action_home_to_search)
                    navController.navigate(R.id.action_home_to_favorites)
                }

                R.id.nav_browse -> {
                    navController.navigate(R.id.action_browse_to_home)
                    navController.navigate(R.id.action_browse_to_search)
                    navController.navigate(R.id.action_browse_to_favorites)
                }

                R.id.nav_search -> {
                    navController.navigate(R.id.action_search_to_home)
                    navController.navigate(R.id.action_search_to_browse)
                    navController.navigate(R.id.action_search_to_favorites)
                }

                R.id.nav_favorites -> {
                    navController.navigate(R.id.action_favorites_to_home)
                    navController.navigate(R.id.action_favorites_to_browse)
                    navController.navigate(R.id.action_favorites_to_search)
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }

}