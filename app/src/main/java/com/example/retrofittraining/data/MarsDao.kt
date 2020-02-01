package com.example.retrofittraining.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarsDao {
    @Query("SELECT * FROM mars_data_table WHERE id = :key")
    fun get(key: Long): MarsData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(objects: List<MarsData>?)
}