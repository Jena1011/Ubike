package com.app.ubike.network

import com.squareup.moshi.Json

data class Station(
    @Json(name = "sna") val sna: String,
    @Json(name = "sarea") val sarea: String)
