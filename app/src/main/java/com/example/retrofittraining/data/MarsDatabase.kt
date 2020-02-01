package com.example.retrofittraining.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MarsData::class], version = 1, exportSchema = false)
abstract class MarsDatabase : RoomDatabase() {

    abstract val marsDao: MarsDao

    companion object {

        @Volatile
        private var INSTANCE: MarsDatabase? = null

        fun getInstance(context: Context): MarsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MarsDatabase::class.java,
                        "mars_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}