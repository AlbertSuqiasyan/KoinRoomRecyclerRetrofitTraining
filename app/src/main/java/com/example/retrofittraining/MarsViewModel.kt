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
    private var _marsObjects = MutableLiveData<List<MarsData>>()
    var marsObjects: LiveData<List<MarsData>> = _marsObjects

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
            marsItem
        }
    }

    fun createMarsObjects(){
        viewModelScope.launch {
            insertMarsObjects()
        }
    }

    suspend fun insertMarsObjects() {
        return withContext(Dispatchers.IO){
            marsDao.insertAll(_response.value)
        }
    }

}
