package com.example.evconnect.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.FrameLayout
import com.example.evconnect.*
import com.example.evconnect.fragments.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var previousMenuItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()
        openDashboard()
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            if(previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked = true
            previousMenuItem = it
            when(it.itemId){
                R.id.home -> {
                    openDashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.profile -> {
                    val profileFragment= ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,profileFragment)
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.settings -> {
                    val settingsFragment= SettingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, settingsFragment)
                        .commit()
                    supportActionBar?.title="Settings"
                    drawerLayout.closeDrawers()
                }
                R.id.aboutApp -> {
                    val aboutAppFragment= AboutAppFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, aboutAppFragment)
                        .commit()
                    supportActionBar?.title="About App"
                    drawerLayout.closeDrawers()
                }
                R.id.support -> {
                    val supportFragment= SupportFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,supportFragment)
                        .commit()
                    supportActionBar?.title="Support"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }

    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Book Hub"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openDashboard(){
        val dashboardFragment= HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, dashboardFragment)
            .addToBackStack("Dashboard")
            .commit()
        supportActionBar?.title="EV Connect"
        navigationView.setCheckedItem(R.id.home)
    }
    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag){
            !is HomeFragment -> openDashboard()

            else->{
                finish()
                super.onBackPressed()
            }
        }
    }
}