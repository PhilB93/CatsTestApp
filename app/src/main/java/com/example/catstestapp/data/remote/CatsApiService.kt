package com.example.catstestapp.data.remote

import androidx.annotation.IntRange
import com.example.catstestapp.BuildConfig.API_KEY
import com.example.catstestapp.domain.model.Cat
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val VALUE_LIMIT = 10

interface CatsApiService {
    @GET("breeds")
    @Headers("x-api-key: $API_KEY")
    suspend fun getCatItems(
        @Query("page")
        @IntRange(from = 1)
        page: Int,
        @Query("limit")
        @IntRange(
            from = 1,
            to = VALUE_LIMIT.toLong()
        )
        limit: Int
    ): List<Cat>
}
