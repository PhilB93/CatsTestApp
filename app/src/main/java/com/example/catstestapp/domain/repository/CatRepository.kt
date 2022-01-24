package com.example.catstestapp.domain.repository

import androidx.paging.PagingData
import com.example.catstestapp.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    fun getListOfCats(): Flow<PagingData<Cat>>

    suspend fun getListOfFavorites(): Flow<List<Cat>>

    suspend fun addCatToFavorites(cat: Cat)

    suspend fun deleteCatFromFavorites(cat: Cat)
}
