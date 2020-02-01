package com.example.retrofittraining

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofittraining.data.MarsData
import com.example.retrofittraining.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MarsViewModel : ViewModel() {

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


}
