package com.example.mim

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
data class Order_sqlite(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "qty") val qty: String?,
    @ColumnInfo(name = "price") val price: Int?,
    @ColumnInfo(name = "size") val size: String?,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "image") val image: String?
)
