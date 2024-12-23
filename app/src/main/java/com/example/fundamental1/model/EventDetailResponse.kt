package com.example.fundamental1.model

import com.google.gson.annotations.SerializedName

data class EventDetailResponse(
    @SerializedName("error")
    val error: Boolean? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("event")
    val event: EventDetail? = null
)

data class EventDetail(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("summary")
    val summary: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("imageLogo")
    val imageLogo: String? = null,

    @SerializedName("mediaCover")
    val mediaCover: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("ownerName")
    val ownerName: String? = null,

    @SerializedName("cityName")
    val cityName: String? = null,

    @SerializedName("quota")
    val quota: Int? = null,

    @SerializedName("registrants")
    val registrants: Int? = null,

    @SerializedName("beginTime")
    val beginTime: String? = null,

    @SerializedName("endTime")
    val endTime: String? = null,

    @SerializedName("link")
    val link: String? = null
)