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
    private val _originalStations  = MutableLiveData<List<Station>>()
    val originalStations: LiveData<List<Station>> = _originalStations
    private val _displayedStations  = MutableLiveData<List<Station>>()
    val displayedStations: LiveData<List<Station>> = _displayedStations

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
                _originalStations.value = StationApi.retrofitService.getStations()
                _status.value = StationsApiStatus.DONE
                _displayedStations.value = originalStations.value
            } catch (e: Exception) {
                _originalStations.value = listOf()
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


    /**
     * 取得包含關鍵字的站點資料
     */
    fun queryStationsByKeyword(str: String){
        _displayedStations.value?.toMutableList()?.clear()
        if(str.isNotEmpty()){
            _displayedStations.value = originalStations.value?.filter { station: Station -> station.sna.contains(str) || station.sarea.contains(str) }
        }else{
            _displayedStations.value = originalStations.value
        }
    }
}