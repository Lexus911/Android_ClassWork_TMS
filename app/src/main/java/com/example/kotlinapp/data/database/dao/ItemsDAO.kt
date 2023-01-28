package com.example.kotlinapp.data.database.dao

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.migration.Migration
import com.example.kotlinapp.data.database.FavoritesEntity
import com.example.kotlinapp.data.database.ItemsEntity


@Dao
interface ItemsDAO {

    @Insert
    fun insertItemsEntity(itemsEntity: ItemsEntity)

    @Query("SELECT(SELECT COUNT(*) From ItemsEntity) != 0") //если БД пустая добавит элемент, если не пустая, то нет
    fun doesItemsEntityExist(): Boolean

    @Query("SELECT * From ItemsEntity")
    fun getItemsEntities(): List<ItemsEntity>

    @Query("DELETE FROM ItemsEntity WHERE description =:description")
    fun deleteItemEntityByDescription(description: String)

    @Query("SELECT * From ItemsEntity WHERE description = :searchText")
    fun findItemEntityByDescription(searchText: String): ItemsEntity


    @Insert(onConflict = IGNORE) // игнор элемента если они одинаковые
    fun insertFavoritesEntity(favoritesEntity: FavoritesEntity)

    @Query("SELECT * From FavoritesEntity")
    fun getFavoritesEntities(): List<FavoritesEntity>

}