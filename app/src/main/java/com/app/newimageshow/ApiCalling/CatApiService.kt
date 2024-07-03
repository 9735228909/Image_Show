package com.app.newimageshow.ApiCalling

import com.app.newimageshow.Model.CatImage
import retrofit2.http.GET

interface CatApiService {


    @GET("images/search")
    suspend fun getRandomCatImage(): List<CatImage>

}