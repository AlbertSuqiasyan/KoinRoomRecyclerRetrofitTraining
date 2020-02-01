package com.example.retrofittraining

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofittraining.data.MarsDao
import com.example.retrofittraining.data.MarsData
import com.example.retrofittraining.network.MarsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarsViewModel(val marsDao: MarsDao) : ViewModel() {

    private val _response = MutableLiveData<List<MarsData>>()
    val response: LiveData<List<MarsData>> = _response
    private val _error = MutableLiveData<String>()
    private var _marsObject = MutableLiveData<MarsData>()
    var marsObject: LiveData<MarsData> = _marsObject

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

    fun getMarsById(marsId: Long) {
        viewModelScope.launch {
            _marsObject.value = getMars(marsId)
        }
    }

    suspend fun getMars(marsId: Long): MarsData? {
        return withContext(Dispatchers.IO){
            val marsItem = marsDao.get(marsId)
            //marsItem is always null for some reason, probably coz json is giving ID as String and my Room Dao get method have Long as Parameter , proly just cannot find it
            marsItem
        }
    }
}