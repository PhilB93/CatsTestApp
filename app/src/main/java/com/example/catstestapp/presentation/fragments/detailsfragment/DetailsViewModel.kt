package com.example.catstestapp.presentation.fragments.detailsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catstestapp.domain.use_case.AddFavoriteCatUseCase
import com.example.catstestapp.domain.use_case.DeleteFavoriteCatUseCase
import com.example.catstestapp.domain.use_case.GetFavoriteCatsUseCase
import com.example.catstestapp.presentation.model.CatUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase,
    private val deleteFavoriteCatUseCase: DeleteFavoriteCatUseCase,
    private val addFavoriteCatUseCase: AddFavoriteCatUseCase
) : ViewModel() {

    val isFavorite = MutableStateFlow(false)

    fun changeIsFavorite() {
        isFavorite.value = !isFavorite.value
    }

    fun checkFavorite(cat: CatUI) {
        viewModelScope.launch {
            getFavoriteCatsUseCase().collectLatest { list ->
                if (list.map {
                        it.toCatUI()
                    }.contains(cat))
                    isFavorite.value = true
            }
        }
    }

    fun deleteFromFavorites(cat: CatUI) =
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteCatUseCase(cat.toCat())
        }

    fun addToFavorites(cat: CatUI) =
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteCatUseCase(cat.toCat())
        }
}

