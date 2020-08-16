package com.twenk11k.stocks.network

import com.skydoves.sandwich.ApiResponse
import com.twenk11k.stocks.model.HandshakeRequest
import com.twenk11k.stocks.model.HandshakeResponse
import com.twenk11k.stocks.model.StocksRequest
import com.twenk11k.stocks.model.StocksResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface StocksService {

    @POST("/api/handshake/start")
    suspend fun fetchHandshakeResponse(
        @Body body: HandshakeRequest
    ): ApiResponse<HandshakeResponse>

    @POST("/api/stocks/list")
    suspend fun fethchStocksAndIndicesResponse(
        @Header("X-VP-Authorization") authorization: String,
        @Body body: StocksRequest
    ): ApiResponse<StocksResponse>

}