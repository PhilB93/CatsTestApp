package com.example.catstestapp.domain.repository

import androidx.paging.PagingData
import com.example.catstestapp.data.remote.model.CatDto
import com.example.catstestapp.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
     fun getListOfCats():Flow<PagingData<Cat>>
}
