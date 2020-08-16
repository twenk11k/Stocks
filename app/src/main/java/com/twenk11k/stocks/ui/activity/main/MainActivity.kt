package com.twenk11k.stocks.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.twenk11k.stocks.R
import com.twenk11k.stocks.databinding.ActivityMainBinding
import com.twenk11k.stocks.ui.activity.DataBindingActivity
import com.twenk11k.stocks.ui.activity.stocks.StocksActivity

class MainActivity: DataBindingActivity() {

    private lateinit var buttonStocks: Button

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
    }

    private fun setViews() {
        buttonStocks = binding.buttonStocks
        setListeners()
    }

    private fun setListeners() {
        buttonStocks.setOnClickListener {
            startStocksActivity()
        }
    }

    private fun startStocksActivity() {
        val intent = Intent(this@MainActivity, StocksActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}