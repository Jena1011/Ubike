package com.app.ubike.network

import com.squareup.moshi.Json

// 定義 Station 資料類別，用於儲存站點資訊
data class Station(
    @Json(name = "sna") val rawSna: String, // 原始站點名稱
    @Json(name = "sarea") val sarea: String // 所屬地區
    ) {
    val sna: String
        get() = rawSna.substring(11) // 處理後的站點名稱
}
