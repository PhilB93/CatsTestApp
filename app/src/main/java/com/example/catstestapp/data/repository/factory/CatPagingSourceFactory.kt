package com.example.catstestapp.data.repository.factory

import com.example.catstestapp.data.remote.CatsApiService
import com.example.catstestapp.data.repository.CatPagingSource
import javax.inject.Inject

class CatPagingSourceFactory @Inject constructor(private val catApiService: CatsApiService) {
    fun create() = CatPagingSource(catApiService)
}