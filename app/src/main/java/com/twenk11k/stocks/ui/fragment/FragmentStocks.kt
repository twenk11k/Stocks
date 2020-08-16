package com.twenk11k.stocks.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twenk11k.stocks.ui.activity.stocks.StocksActivity
import com.twenk11k.stocks.R
import com.twenk11k.stocks.databinding.FragmentStocksBinding
import kotlinx.android.synthetic.main.app_bar_stocks.*

class FragmentStocks: DataBindingFragment() {

    private lateinit var binding: FragmentStocksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.fragment_stocks, container)
        Log.d("FragmentStocks",(activity as StocksActivity).toolbar.title.toString())
        return binding.root
    }

}