package com.twenk11k.stocks.ui.activity.stockdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.twenk11k.stocks.model.StockDetailsResponse
import com.twenk11k.stocks.repository.StockDetailsRepository

class StockDetailsViewModel @ViewModelInject constructor(
    private val stockDetailsRepository: StockDetailsRepository
): ViewModel() {

    suspend fun retrieveStockDetailsResponse(id: Int, aesKey: String, aesIV: String, authorization: String): LiveData<StockDetailsResponse> {
        return stockDetailsRepository.retrieveStockDetailsResponse(id, aesKey, aesIV, authorization).asLiveData()
    }

}