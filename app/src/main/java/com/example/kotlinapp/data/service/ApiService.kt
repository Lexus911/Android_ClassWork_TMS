package com.example.kotlinapp.data.service

import com.example.kotlinapp.data.model.ItemsResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("/nkuYRM")
    fun getData(): Single<ItemsResponse>
}