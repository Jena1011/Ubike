package com.app.ubike.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// API 來源 URL
private const val BASE_URL =
    "https://tcgbusfs.blob.core.windows.net/dotapp/youbike/v2/"

/**
 * 使用 Kotlin 適配器工廠構建 Moshi 對象，用于 Retrofit。
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * 使用 Moshi 轉換器的 Retrofit 對象。
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * 一個公開介面包含 [getStations] 方法
 */
interface StationsApiService {
    /**
     * 回傳 [Station] 列表資料
     */
    @GET("youbike_immediate.json")
    suspend fun getStations() : List<Station>

}

/**
 * 定義 StationApi 物件，它提供對 StationsApiService 的存取
 */
object StationApi {
    val retrofitService: StationsApiService by lazy { retrofit.create(StationsApiService::class.java) }
}