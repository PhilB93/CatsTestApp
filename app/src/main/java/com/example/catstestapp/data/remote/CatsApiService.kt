package com.example.catstestapp.data.remote

import com.example.catstestapp.BuildConfig.API_KEY
import com.example.catstestapp.data.remote.model.CatDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatsApiService {
    @GET("breeds")
    @Headers("x-api-key: $API_KEY")
    suspend fun getCatItems(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<CatDto>
}
