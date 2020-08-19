package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockDetailsResponse(
    @field:Json(name = "isDown") val isDown: Boolean,
    @field:Json(name = "isUp") val isUp: Boolean,
    @field:Json(name = "bid") val bId: Float,
    @field:Json(name = "channge") val change: Float,
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "difference") val difference: Float,
    @field:Json(name = "offer") val offer: Float,
    @field:Json(name = "highest") val highest: Float,
    @field:Json(name = "lowest") val lowest: Float,
    @field:Json(name = "maximum") val maximum: Float,
    @field:Json(name = "minimum") val minimum: Float,
    @field:Json(name = "price") val price: Float,
    @field:Json(name = "volume") val volume: Double,
    @field:Json(name = "symbol") var symbol: String,
    @field:Json(name = "graphicData") val graphicData: List<GraphicData>,
    @field:Json(name = "status") val status: Status
)