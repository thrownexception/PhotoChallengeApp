package com.te.photochallenge.di

import com.te.photochallenge.model.apiService.PhotoApiService
import com.te.photochallenge.view.adapter.PhotoListAdapter
import com.te.photochallenge.viewmodel.PhotoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    viewModel { PhotoListViewModel(get()) }
    factory { PhotoApiService() }
    factory { params -> PhotoListAdapter(list = params.get()) }
}