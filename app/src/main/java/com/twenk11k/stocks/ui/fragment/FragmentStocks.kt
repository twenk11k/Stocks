package com.twenk11k.stocks.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twenk11k.stocks.R
import com.twenk11k.stocks.databinding.FragmentStocksBinding
import com.twenk11k.stocks.extension.gone
import com.twenk11k.stocks.ui.activity.stocks.StocksActivity
import com.twenk11k.stocks.util.Utils.getPeriodName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_stocks.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentStocks : DataBindingFragment() {

    private lateinit var editSearch: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var adapterStocks: StocksAdapter

    private lateinit var binding: FragmentStocksBinding

    val viewModel: StocksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = binding(inflater, R.layout.fragment_stocks, container)
        setViews()
        val periodName = getPeriodName(requireContext(), (activity as StocksActivity).toolbar.title.toString())
        lifecycleScope.launch {
            viewModel.retrieveStocksResponse(periodName).observe(viewLifecycleOwner, Observer {
                if (it.stocks.isNotEmpty()) {
                    progressBar.gone()
                    adapterStocks.addListStock(it.stocks, it.aesKey, it.aesIv)
                }
                Log.d("FragmentStocks", it.toString())
            })
        }
        Log.d("FragmentStocks", periodName)
        return binding.root
    }

    private fun setViews() {
        editSearch = binding.editSearch
        recyclerView = binding.recyclerView
        progressBar = binding.progressBar
        setAdapter()
        setRecyclerView()
    }

    private fun setAdapter() {
        adapterStocks = StocksAdapter(requireContext())
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapterStocks
    }

}