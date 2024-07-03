package com.app.newimageshow.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newimageshow.ApiCalling.CatApiService
import com.app.newimageshow.Model.CatImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatImageViewModel: ViewModel() {

    private val _catImage = MutableLiveData<String>()
    val catImage: LiveData<String> = _catImage

//    init {
//        fetchCatImage()
//    }

    fun fetchCatImage() {
        viewModelScope.launch {
            try {
                val image = getRandomCatImage()
                _catImage.value = image.url
            } catch (e: Exception) {
                _catImage.value = ""
            }
        }
    }

    private suspend fun getRandomCatImage(): CatImage {
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(CatApiService::class.java)
            service.getRandomCatImage().first()
        }
    }

}