package com.twenk11k.stocks.ui.activity.stocks

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.twenk11k.stocks.R
import com.twenk11k.stocks.databinding.ActivityStocksBinding
import com.twenk11k.stocks.ui.activity.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_stocks.view.*

@AndroidEntryPoint
class StocksActivity : DataBindingActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appBarStocks: View
    private lateinit var toolbar: Toolbar

    private val binding: ActivityStocksBinding by binding(R.layout.activity_stocks)

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()

    }

    private fun setViews() {
        appBarStocks = binding.appBarStocks
        toolbar = appBarStocks.toolbar
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout
        navView = binding.navView
        setupActionBarWithNavController()
    }

    private fun setupActionBarWithNavController() {
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_hisse_ve_endeksler,
                R.id.nav_yukselenler,
                R.id.nav_dusenler,
                R.id.nav_hacme_gore_30,
                R.id.nav_hacme_gore_50,
                R.id.nav_hacme_gore_100
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}