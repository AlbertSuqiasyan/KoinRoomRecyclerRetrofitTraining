package com.example.retrofittraining

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittraining.data.MarsData
import com.example.retrofittraining.network.MarsApi
import kotlinx.coroutines.launch

class ClickedItemViewModel : ViewModel() {

    private val _response = MutableLiveData<List<MarsData>>()
    val response: LiveData<List<MarsData>> = _response
    private val _error = MutableLiveData<String>()

    init {
        getMarsProperties()
    }

    private fun getMarsProperties() {
        viewModelScope.launch {
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = listResult
            } catch (t: Throwable) {
                _error.value = t.message
            }
        }
    }

    fun getMarsData(marsDataList: List<MarsData>?, marsId: String): MarsData? {
        return marsDataList?.find { it.id == marsId }
    }

}