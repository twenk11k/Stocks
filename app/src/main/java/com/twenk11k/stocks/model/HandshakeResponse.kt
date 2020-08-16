package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HandshakeResponse(
    @field:Json(name = "aesKey") val aesKey: String,
    @field:Json(name = "aesIV") val aesIV: String,
    @field:Json(name = "authorization") val authorization: String,
    @field:Json(name = "lifeTime") val lifeTime: String,
    @field:Json(name = "status") val status: Status
)