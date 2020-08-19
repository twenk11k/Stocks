package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockDetailsRequest(
    @field:Json(name = "id") val id: String
)