package com.example.catstestapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catstestapp.data.repository.factory.CatPagingSourceFactory
import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CatRepositoryImpl @Inject constructor(private val factory: CatPagingSourceFactory) :
    CatRepository {
    override fun getListOfCats(): Flow<PagingData<Cat>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 50,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { factory.create() }
    ).flow
}