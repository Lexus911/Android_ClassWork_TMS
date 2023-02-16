package com.example.kotlinapp.domain.items


import com.example.kotlinapp.model.FavoritesModel
import com.example.kotlinapp.model.ItemsModel
import io.reactivex.Completable
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
     fun getData(): Completable

     fun showData(): Observable<List<ItemsModel>>

    suspend fun deleteItemByDescription(description: String)

    suspend fun findItemByDescription(searchText: String): ItemsModel

    suspend fun favClicked(itemsModel: ItemsModel)

    suspend fun getFavorites(): List<FavoritesModel>
}