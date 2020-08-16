package com.twenk11k.stocks.network

import com.twenk11k.stocks.model.HandshakeRequest
import com.twenk11k.stocks.model.StocksRequest
import javax.inject.Inject

class StocksClient @Inject constructor(private val stocksService: StocksService) {

    suspend fun fetchHandshakeResponse(handshakeRequest: HandshakeRequest) =
        stocksService.fetchHandshakeResponse(handshakeRequest)

    suspend fun fetchStocksResponse(authorization: String, stocksRequest: StocksRequest) =
        stocksService.fetchStocksResponse(authorization, stocksRequest)

}