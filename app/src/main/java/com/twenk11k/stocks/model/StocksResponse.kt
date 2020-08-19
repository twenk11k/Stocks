package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StocksResponse(
    @field:Json(name = "stocks") val stocks: List<Stock>,
    @field:Json(name = "status") val status: Status,
    var aesKey: String = "",
    var aesIV: String = "",
    var authorization: String = ""
)
