package com.example.fundamental1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamental1.model.EventDetailResponse
import com.example.fundamental1.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _eventDetails = MutableLiveData<EventDetailResponse>()
    val eventDetails: LiveData<EventDetailResponse> get() = _eventDetails

    fun fetchEventDetails(eventId: Int) {
        viewModelScope.launch {
            RetrofitClient.instance.getEventDetails(eventId).enqueue(object : Callback<EventDetailResponse> {
                override fun onResponse(call: Call<EventDetailResponse>, response: Response<EventDetailResponse>) {
                    if (response.isSuccessful) {
                        _eventDetails.value = response.body()
                    }
                }

                override fun onFailure(call: Call<EventDetailResponse>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}