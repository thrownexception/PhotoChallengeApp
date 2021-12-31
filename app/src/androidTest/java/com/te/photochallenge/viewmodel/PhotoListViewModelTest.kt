package com.te.photochallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.te.photochallenge.di.dataModule
import com.te.photochallenge.model.Photo
import com.te.photochallenge.model.apiService.PhotoApiService
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class PhotoListViewModelTest : KoinTest{

    val apiService by inject<PhotoApiService>()
    val viewModel by inject<PhotoListViewModel>()
    val photo1 = Photo(1,1, "title", "url", "thumbnailUrl")
    val photo2 = Photo(2,2, "title2", "url2", "thumbnailUrl2")
    val photos = MutableLiveData<List<Photo>>()

    @get:Rule
    val koinTestRule = KoinTestRule.create{
        printLogger()
        modules(dataModule)
    }

    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @Test
    fun isLiveDataEmitting(){

    }
}