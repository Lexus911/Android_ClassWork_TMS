package com.example.kotlinapp.data.items


import android.util.Log
import com.example.kotlinapp.data.database.ItemsEntity
import com.example.kotlinapp.data.database.dao.ItemsDAO
import com.example.kotlinapp.data.service.ApiService
import com.example.kotlinapp.data.service.ApiServiceSecond
import com.example.kotlinapp.domain.items.ItemsRepository
import com.example.kotlinapp.model.ItemsModel
import kotlinx.coroutines.Dispatchers
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

        return withContext(Dispatchers.IO) {

            if(!itemsDAO.doesItemsEntityExist()){
                Log.w("getData", "data not exist")
                val response = apiService.getData()
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

    override suspend fun showData(): List<ItemsModel> {
        return withContext(Dispatchers.IO) {
            val itemsEntity = itemsDAO.getItemsEntities()
            itemsEntity.map{
                ItemsModel(it.description, it.imageUrl)
            }
        }
    }

    override suspend fun deleteItemByDescription(description: String) {
        withContext(Dispatchers.IO) {
            itemsDAO.deleteItemEntityByDescription(description)
        }
    }

//    override suspend fun findItemByDescription(searchText: String): ItemsModel {
//        return withContext(Dispatchers.IO) {
//            val itemsEntity = itemsDAO.findItemEntityByDescription(searchText)
//        }
//    }
}
