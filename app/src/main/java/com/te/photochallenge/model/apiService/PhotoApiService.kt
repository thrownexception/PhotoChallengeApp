package com.te.photochallenge.model.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotoApiService {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PhotoApi::class.java)
}