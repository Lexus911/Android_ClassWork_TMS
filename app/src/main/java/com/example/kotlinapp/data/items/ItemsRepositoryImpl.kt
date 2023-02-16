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
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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

    private val compositeDisposable = CompositeDisposable()

    override fun getData(): Completable {

       return itemsDAO.doesItemsEntityExist()
            .subscribeOn(Schedulers.io())
            .doAfterNext{
                if(!it){
                    val response = apiService.getData()
                    val getData = response.subscribeOn(Schedulers.io())
                        .doAfterSuccess {
                            it.sampleList.forEach {
                                val itemsEntity =
                                    ItemsEntity(Random().nextInt(999 - 1), it.description, it.imageUrl)
                                itemsDAO.insertItemsEntity(itemsEntity)
                            }
                        }
                        .doOnError {
                            Log.w("error", "when making request")
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                    compositeDisposable.add(getData)
                }
            }
           .doOnComplete{
               compositeDisposable.dispose()
           }
           .ignoreElements()
           .observeOn(AndroidSchedulers.mainThread())
    }


    override fun showData(): io.reactivex.Observable<List<ItemsModel>> {
            val itemsEntity = itemsDAO.getItemsEntities()
       return itemsEntity.subscribeOn(Schedulers.io())
            .map {
                it.map {
                    ItemsModel(it.description, it.imageUrl)
                }
            }
                    .observeOn(AndroidSchedulers.mainThread())
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
