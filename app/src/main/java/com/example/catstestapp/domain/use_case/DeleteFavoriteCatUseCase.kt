package com.example.catstestapp.domain.use_case

import com.example.catstestapp.domain.model.Cat
import com.example.catstestapp.domain.repository.CatRepository
import javax.inject.Inject

class DeleteFavoriteCatUseCase @Inject constructor(private val catRepository: CatRepository) {
    suspend operator fun invoke(cat: Cat) =catRepository.deleteCatFromFavorites(cat)
}