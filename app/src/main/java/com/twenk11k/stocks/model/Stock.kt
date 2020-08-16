package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Stock(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "isDown") val isDown: Boolean,
    @field:Json(name = "isUp") val isUp: Boolean,
    @field:Json(name = "bid") val bId: Float,
    @field:Json(name = "difference") val difference: Float,
    @field:Json(name = "offer") val offer: Float,
    @field:Json(name = "price") val price: Float,
    @field:Json(name = "volume") val volume: Float,
    @field:Json(name = "symbol") val symbol: String
    )