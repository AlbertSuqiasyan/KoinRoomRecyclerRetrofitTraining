package com.example.retrofittraining.data


import com.squareup.moshi.Json



data class MarsData(
    @Json(name = "id")
    val id: String,
    @Json(name = "img_src")
    val img_src: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "type")
    val type: String
)