package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StocksResponse(
    @field:Json(name = "stocks") val stocks: List<Stocks>,
    @field:Json(name = "status") val status: Status
)
