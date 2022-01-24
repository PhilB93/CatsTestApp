package com.example.catstestapp.domain.use_case

import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetFavoriteCatsUseCase @Inject constructor(private val catRepository: CatRepository) {
    suspend operator fun invoke(): Flow<List<Cat>> =catRepository.getListOfFavorites()
}