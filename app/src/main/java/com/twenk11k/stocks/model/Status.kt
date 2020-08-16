package com.twenk11k.stocks.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Status(
    @field:Json(name = "isSuccess") val isSuccess: Boolean,
    @field:Json(name = "error") val error: Error
)