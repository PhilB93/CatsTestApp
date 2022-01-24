package com.example.catstestapp.presentation.fragments.favoritesfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catstestapp.domain.use_case.GetFavoriteCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase
) : ViewModel() {

    suspend fun getList() = getFavoriteCatsUseCase().map { list ->
        list.map {
            it.toCatUI()
        }
    }.stateIn(viewModelScope)
}



