package com.example.retrofittraining.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MarsDao {
    @Query("SELECT * FROM mars_data_table WHERE id = :key")
    fun get(key: Long): MarsData?
}