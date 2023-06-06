package com.app.ubike.network

import com.squareup.moshi.Json

data class Station(
    @Json(name = "sna") val rawSna: String,
    @Json(name = "sarea") val sarea: String,) {
    val sna: String
        get() = rawSna.substring(11)
}
