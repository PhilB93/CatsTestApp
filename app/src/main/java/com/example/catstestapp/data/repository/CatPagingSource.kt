package com.example.catstestapp.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catstestapp.data.remote.CatsApiService
import com.example.catstestapp.data.remote.model.toCat
import com.example.catstestapp.domain.model.Cat

class CatPagingSource(
    private val catApiService: CatsApiService,
) : PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int {
        return CAT_STARTING_PAGE_INDEX
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val position = params.key ?: CAT_STARTING_PAGE_INDEX

        return try {
            val photos: List<Cat> =
                catApiService.getCatItems(params.loadSize, position).map { it.toCat() }
            Log.d("CatPagingSource", photos.size.toString())
            LoadResult.Page(
                data = photos,
                prevKey = if (position == CAT_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val CAT_STARTING_PAGE_INDEX = 1
    }
}