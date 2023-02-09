package com.example.kotlinapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.kotlinapp.data.database.FavoritesEntity
import com.example.kotlinapp.data.database.ItemsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemsDAO {

    @Insert
    fun insertItemsEntity(itemsEntity: ItemsEntity)

    @Query("SELECT(SELECT COUNT(*) From ItemsEntity) != 0") //если БД пустая добавит элемент, если не пустая, то нет
    fun doesItemsEntityExist(): Flow<Boolean>

    @Query("SELECT * From ItemsEntity")
    fun getItemsEntities(): Flow<List<ItemsEntity>>

    @Query("DELETE FROM ItemsEntity WHERE description =:description")
    fun deleteItemEntityByDescription(description: String)

    @Query("SELECT * From ItemsEntity WHERE description = :searchText")
    fun findItemEntityByDescription(searchText: String): ItemsEntity

    @Query("UPDATE ItemsEntity SET isFavorite =:isFavorite WHERE description =:description")
    fun addToFavorite(description: String, isFavorite: Boolean )


    @Insert(onConflict = IGNORE) // игнор элемента если они одинаковые
    fun insertFavoritesEntity(favoritesEntity: FavoritesEntity)

    @Query("SELECT * From FavoritesEntity")
    fun getFavoritesEntities(): List<FavoritesEntity>

}