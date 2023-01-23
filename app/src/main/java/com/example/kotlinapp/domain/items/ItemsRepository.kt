package com.example.kotlinapp.domain.items


import com.example.kotlinapp.model.ItemsModel

interface ItemsRepository {
    suspend fun getData()

    suspend fun showData(): List<ItemsModel>

    suspend fun deleteItemByDescription(description: String)

    suspend fun findItemByDescription(searchText: String): ItemsModel
}