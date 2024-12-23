package com.example.fundamental1.network

import com.example.fundamental1.model.EventResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("events?active=1")
    fun getUpcomingEvents(): Call<EventResponse>

    @GET("events?active=0")
    fun getFinishedEvents(): Call<EventResponse>
}