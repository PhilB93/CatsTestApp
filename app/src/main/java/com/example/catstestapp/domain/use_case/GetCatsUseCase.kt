package com.example.catstestapp.domain.use_case

import androidx.paging.PagingData
import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val catRepository: CatRepository) {
    fun execute(): Flow<PagingData<Cat>> {
        return catRepository.getListOfCats()
    }
}