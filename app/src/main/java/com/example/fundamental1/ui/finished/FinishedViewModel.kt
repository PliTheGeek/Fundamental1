package com.example.fundamental1.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamental1.model.EventResponse
import com.example.fundamental1.model.ListEventsItem
import com.example.fundamental1.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishedViewModel : ViewModel() {

    private val _finishedEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedEvents: LiveData<List<ListEventsItem>> get() = _finishedEvents

    fun fetchFinishedEvents() {
        viewModelScope.launch {
            RetrofitClient.instance.getFinishedEvents().enqueue(object : Callback<EventResponse> {
                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                    if (response.isSuccessful) {
                        _finishedEvents.value = response.body()?.listEvents ?: listOf()
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}