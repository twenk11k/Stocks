package com.twenk11k.stocks.ui.activity.stocks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.twenk11k.stocks.model.HandshakeResponse
import com.twenk11k.stocks.repository.StocksRepository

class StocksViewModel  @ViewModelInject constructor(
    private val stocksRepository: StocksRepository
): ViewModel() {

    suspend fun requestHandshake(): LiveData<HandshakeResponse> {
        return stocksRepository.requestHandshake().asLiveData()
    }

}
