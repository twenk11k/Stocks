package com.twenk11k.stocks.network

import com.skydoves.sandwich.ApiResponse
import com.twenk11k.stocks.model.HandshakeRequest
import com.twenk11k.stocks.model.HandshakeResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface StocksService {

    @Headers("Content-Type: application/json")
    @POST("/api/handshake/start")
    suspend fun fetchHandshakeResponse(
        @Body body: HandshakeRequest
        ): ApiResponse<HandshakeResponse>

}