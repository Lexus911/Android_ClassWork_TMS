package com.example.kotlinapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemsEntity")
data class ItemsEntity(

    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("imageUrl")
    val imageUrl: String
)
