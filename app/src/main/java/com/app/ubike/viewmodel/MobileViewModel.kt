package com.app.ubike.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ubike.network.Station
import com.app.ubike.network.StationApi
import kotlinx.coroutines.*

/**
 * 用於表示 StationsApi 的狀態的列舉類別，包含 LOADING, ERROR, DONE 三種狀態。
 */
enum class StationsApiStatus { LOADING, ERROR, DONE }

/**
 * [ViewModel] 子類，用於管理 MobileFragment 相關的數據和邏輯。
 */
class MobileViewModel : ViewModel() {

    private val _status = MutableLiveData<StationsApiStatus>()
    val status: LiveData<StationsApiStatus> = _status
    private val _stations  = MutableLiveData<List<Station>>()
    val stations: LiveData<List<Station>> = _stations

    /**
     * 初始化，獲取站點數據。
     */
    init {
        getStations()
    }

    /**
     * 用於從 [StationApi] 擷取站點資料
     */
    private fun getStations() {
        viewModelScope.launch {
            _status.value = StationsApiStatus.LOADING
            try {
                _stations.value = StationApi.retrofitService.getStations()
                _status.value = StationsApiStatus.DONE
            } catch (e: Exception) {
                _stations.value = listOf()
                _status.value = StationsApiStatus.ERROR
                Log.d("MobileViewModel",e.message.toString())
            }
            Log.d("MobileViewModel",status.toString())
        }
    }

    /**
     * 暫停函式，用於從 [StationApi] 擷取站點資料
     */
    suspend fun fetchStations(): List<Station> = CoroutineScope(Dispatchers.IO).async {
        return@async StationApi.retrofitService.getStations()
    }.await()
}