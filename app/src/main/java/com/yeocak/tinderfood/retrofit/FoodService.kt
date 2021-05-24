package com.yeocak.tinderfood.retrofit

import com.yeocak.tinderfood.model.recipes.RetrofitRandomFood
import com.yeocak.tinderfood.utils.RetrofitInstance
import retrofit2.Response
import retrofit2.http.GET

interface FoodService {

    @GET("/recipes/random?apiKey=${RetrofitInstance.API_KEY}&number=15")
    suspend fun getFood(): Response<RetrofitRandomFood>
}