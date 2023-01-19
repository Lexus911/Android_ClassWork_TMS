package com.example.kotlinapp.data.model

import com.google.gson.annotations.SerializedName

data class ItemsResponse(

    @SerializedName("sample")
    val sampleList: List<Sample>
)

data class Sample(

    @SerializedName("description")
    val description: String,

    @SerializedName("image-url")
    val imageUrl: String
)