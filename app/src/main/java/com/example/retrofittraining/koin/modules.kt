package com.example.retrofittraining.koin

import androidx.room.Room
import com.example.retrofittraining.MarsViewModel
import com.example.retrofittraining.data.MarsDao
import com.example.retrofittraining.data.MarsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marsModule = module {
    viewModel {
        MarsViewModel(get())
    }
    single<MarsDao> {
        Room.databaseBuilder(androidApplication(),MarsDatabase::class.java,"mars_history_database")
            .build()
            .marsDao
    }
}