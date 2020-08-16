package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HandshakeRequest(
    @field:Json(name = "deviceId") val deviceId: String,
    @field:Json(name = "systemVersion") val systemVersion: String,
    @field:Json(name = "platformName") val platformName: String,
    @field:Json(name = "deviceModel") val deviceModel: String,
    @field:Json(name = "manifacturer") val manufacturer: String
)