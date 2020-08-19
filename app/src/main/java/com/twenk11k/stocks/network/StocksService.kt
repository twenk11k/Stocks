package com.twenk11k.stocks.network

import com.skydoves.sandwich.ApiResponse
import com.twenk11k.stocks.model.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface StocksService {

    @POST("/api/handshake/start")
    suspend fun fetchHandshakeResponse(
        @Body body: HandshakeRequest
    ): ApiResponse<HandshakeResponse>

    @POST("/api/stocks/list")
    suspend fun fetchStocksResponse(
        @Header("X-VP-Authorization") authorization: String,
        @Body body: StocksRequest
    ): ApiResponse<StocksResponse>

    @POST("/api/stocks/detail")
    suspend fun fetchStockDetailsResponse(
        @Header("X-VP-Authorization") authorization: String,
        @Body body: StockDetailsRequest
    ): ApiResponse<StockDetailsResponse>

}