package com.example.catstestapp.domain.use_case

import androidx.paging.PagingData
import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val catRepository: CatRepository) {
    operator fun invoke(): Flow<PagingData<Cat>> = catRepository.getListOfCats()
}