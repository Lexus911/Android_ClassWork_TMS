package com.example.kotlinapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
}