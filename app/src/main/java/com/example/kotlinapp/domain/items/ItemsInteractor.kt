package com.example.kotlinapp.domain.items

import com.example.kotlinapp.model.ItemsModel
import javax.inject.Inject

class ItemsInteractor @Inject constructor(private val itemsRepository: ItemsRepository) {

   suspend fun getData(): List<ItemsModel>{
        return itemsRepository.getData()
    }
}