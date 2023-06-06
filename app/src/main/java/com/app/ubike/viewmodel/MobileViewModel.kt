package com.app.ubike.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ubike.network.Station
import com.app.ubike.network.StationApi
import kotlinx.coroutines.launch

enum class StationsApiStatus { LOADING, ERROR, DONE }

class MobileViewModel : ViewModel() {

    private val _status = MutableLiveData<StationsApiStatus>()
    val status: LiveData<StationsApiStatus> = _status
    private val _stations  = MutableLiveData<List<Station>>()
    val stations: LiveData<List<Station>> = _stations

    init {
        getStations()
    }

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

}