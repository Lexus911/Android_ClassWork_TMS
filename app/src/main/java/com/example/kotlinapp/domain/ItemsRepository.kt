package com.example.kotlinapp.domain

import com.example.kotlinapp.model.ItemsModel

interface ItemsRepository {
    fun getData():List<ItemsModel>
}