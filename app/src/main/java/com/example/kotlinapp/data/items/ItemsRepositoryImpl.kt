package com.example.kotlinapp.data.items

import android.util.Log
import com.example.kotlinapp.data.service.ApiService
import com.example.kotlinapp.data.service.ApiServiceSecond
import com.example.kotlinapp.domain.items.ItemsRepository
import com.example.kotlinapp.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


class ItemsRepositoryImpl @Inject constructor(
    @Named("First")private val apiService: ApiService,
    @Named("Second")private val apiServiceSecond: ApiServiceSecond
    ): ItemsRepository {

    override suspend fun getData(): List<ItemsModel> {

        val responseSecond = apiServiceSecond.getPhotoById(3)
        Log.w("Second response", responseSecond.body()?.title.toString())

        val responseQuery = apiServiceSecond.getPhotoByTitle("culpa odio esse rerum omnis laboriosam voluptate repudiandae")
        Log.w("Query response", responseQuery.body()!!.get(0).toString())

        return withContext(Dispatchers.IO){
        val response = apiService.getData()
            response.body()?.sampleList?.let{
                it.map {
                    ItemsModel(it.description, it.imageUrl)
                }
            } ?: kotlin.run{
                emptyList()
            }
        }
        }
    }
