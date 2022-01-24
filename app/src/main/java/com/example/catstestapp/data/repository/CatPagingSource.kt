package com.example.catstestapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catstestapp.data.remote.CatsApiService
import com.example.catstestapp.domain.model.Cat

class CatPagingSource(
    private val api: CatsApiService
) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        return try {
            val nextPageNumber = params.key ?: CAT_STARTING_PAGE_INDEX
            val response = api
                .getCatItems(page = nextPageNumber, limit = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == CAT_STARTING_PAGE_INDEX) null
                else nextPageNumber - 1,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: retrofit2.HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val CAT_STARTING_PAGE_INDEX = 1
    }
}