package com.example.retrofittraining.koin

import com.example.retrofittraining.MarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marsModule = module {
    viewModel {
        MarsViewModel()
    }
}