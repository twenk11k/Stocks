package com.twenk11k.stocks.ui.fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.twenk11k.stocks.model.StocksResponse
import com.twenk11k.stocks.repository.StocksRepository

class StocksViewModel  @ViewModelInject constructor(
    private val stocksRepository: StocksRepository
): ViewModel() {

    suspend fun retrieveStocksResponse(periodName: String): LiveData<StocksResponse> {
        return stocksRepository.retrieveStocksResponse(periodName).asLiveData()
    }

}
