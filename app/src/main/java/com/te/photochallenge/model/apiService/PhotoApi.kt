package com.te.photochallenge.model.apiService

import com.te.photochallenge.model.Photo
import retrofit2.Call
import retrofit2.http.GET

interface PhotoApi {
    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}