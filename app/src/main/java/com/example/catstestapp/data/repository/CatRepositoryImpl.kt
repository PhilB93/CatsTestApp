package com.example.catstestapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catstestapp.data.local.CatFavoritesDatabase
import com.example.catstestapp.data.repository.factory.CatPagingSourceFactory
import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val factory: CatPagingSourceFactory,
    private val catFavoritesDatabase: CatFavoritesDatabase
) :
    CatRepository {
    override fun getListOfCats(): Flow<PagingData<Cat>> = Pager(
        config = PagingConfig(
            pageSize = 8,
            maxSize = 30,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { factory.create() }
    ).flow

    override suspend fun getListOfFavorites(): Flow<List<Cat>> =
        flow {
            try {
                emit( catFavoritesDatabase.dao.getCatEntity().map { it.toCat() })
            }
            catch (e:Exception) {
                emit(emptyList())
            }
        }



    override suspend fun addCatToFavorites(cat: Cat) {
        catFavoritesDatabase.dao.insertCatEntity(cat.toCatEntity())
    }

    override suspend fun deleteCatFromFavorites(cat: Cat) {
        catFavoritesDatabase.dao.delete(cat.toCatEntity())
    }


}