package com.example.retrofittraining.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "mars_data_table")
data class MarsData(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "img_src")
    val imgSrc: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "type")
    val type: String
)