package com.te.photochallenge.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.te.photochallenge.model.Photo
import com.te.photochallenge.model.apiService.PhotoApiService
import com.te.photochallenge.view.NoDataAccessDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoListViewModel(application: Application) : AndroidViewModel(application) {

    private val apiService: PhotoApiService by inject(PhotoApiService::class.java)
    private var photoList = arrayListOf<Photo>()
    val photos by lazy { MutableLiveData<List<Photo>>() }
    private var noInternetJob: Job? = null

    fun fetchData(fragmentManager: FragmentManager) {

        val call = apiService.api.getPhotos()

        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    response.body()?.let { photoList.addAll(it) }
                    updateLiveData(photoList)
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                val TAG = "NoDataAccessDialog"
                Toast.makeText(
                    getApplication(),
                    "Internet connection problem. \nCheck your internet connection. \nTry refreshing the screen.",
                    Toast.LENGTH_SHORT
                ).show()

                noInternetJob = CoroutineScope(Dispatchers.IO).launch {
                    Thread.sleep(5000)
                    NoDataAccessDialog().show(fragmentManager, TAG)
                }

            }
        })
    }

    private fun updateLiveData(retrievedList: ArrayList<Photo>) {
        photos.value = retrievedList
    }

    override fun onCleared() {
        super.onCleared()
        noInternetJob?.cancel()
    }

}