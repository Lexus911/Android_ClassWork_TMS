package com.example.kotlinapp.domain

import com.example.kotlinapp.model.ItemsModel

class ItemsInteractor(private val itemsRepository: ItemsRepository) {

    fun getData(): List<ItemsModel>{
        return itemsRepository.getData()
    }
}