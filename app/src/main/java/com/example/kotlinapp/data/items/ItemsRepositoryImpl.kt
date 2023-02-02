package com.example.kotlinapp.data.items


import android.util.Log
import com.example.kotlinapp.data.database.FavoritesEntity
import com.example.kotlinapp.data.database.ItemsEntity
import com.example.kotlinapp.data.database.dao.ItemsDAO
import com.example.kotlinapp.data.service.ApiService
import com.example.kotlinapp.data.service.ApiServiceSecond
import com.example.kotlinapp.domain.items.ItemsRepository
import com.example.kotlinapp.model.FavoritesModel
import com.example.kotlinapp.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Named



class ItemsRepositoryImpl @Inject constructor(
    @Named("First")private val apiService: ApiService,
    @Named("Second")private val apiServiceSecond: ApiServiceSecond,
    private val itemsDAO: ItemsDAO

    ): ItemsRepository {

    override suspend fun getData() {
         withContext(Dispatchers.IO) {
            if(!itemsDAO.doesItemsEntityExist()){
                val response = apiService.getData()
                Log.w("Data", response.body()?.sampleList.toString())
                response.body()?.sampleList?.let {
                    it.map {
                        val itemsEntity =
                            ItemsEntity(Random().nextInt(999-1), it.description, it.imageUrl)
                        itemsDAO.insertItemsEntity(itemsEntity)
                    }
                }
            }
        }
    }


    override suspend fun showData(): Flow<List<ItemsModel>> {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDAO.getItemsEntities()
            itemsEntity.map{ itemsList ->  //мапим флоу, доставая лист
                itemsList.map{ item -> //мапим сам лист уже
                ItemsModel(item.description, item.imageUrl)
                }
            }
        }
    }

    override suspend fun deleteItemByDescription(description: String) {
        withContext(Dispatchers.IO) {
            itemsDAO.deleteItemEntityByDescription(description)
        }
    }

    override suspend fun findItemByDescription(searchText: String): ItemsModel {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDAO.findItemEntityByDescription(searchText)
            ItemsModel(itemsEntity.description, itemsEntity.imageUrl)
        }
    }

    override suspend fun favClicked(itemsModel: ItemsModel) {
        return withContext(Dispatchers.IO) {
            itemsDAO.insertFavoritesEntity(
                FavoritesEntity(
                Random().nextInt(),
                itemsModel.description,
                itemsModel.image)
            )
        }
    }

    override suspend fun getFavorites(): List<FavoritesModel> {
        return withContext(Dispatchers.IO) {
            val favoritesEntity = itemsDAO.getFavoritesEntities()
            favoritesEntity.map{
                FavoritesModel(it.description, it.imageUrl)
            }
        }
    }
}
