package com.example.kotlinapp.domain.items

import com.example.kotlinapp.model.ItemsModel

interface ItemsRepository {
    suspend fun getData():List<ItemsModel>
}