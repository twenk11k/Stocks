package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GraphicData(
    @field:Json(name = "day") val day: Int,
    @field:Json(name = "value") val value: Double
)