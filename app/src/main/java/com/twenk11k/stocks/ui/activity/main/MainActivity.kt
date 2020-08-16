package com.twenk11k.stocks.ui.activity.main

import android.os.Bundle
import android.widget.Button
import com.twenk11k.stocks.R
import com.twenk11k.stocks.databinding.ActivityMainBinding
import com.twenk11k.stocks.ui.activity.DataBindingActivity

class MainActivity: DataBindingActivity() {

    private lateinit var buttonStocks: Button

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
    }

    private fun setViews() {
        buttonStocks = binding.buttonStocks
    }

}