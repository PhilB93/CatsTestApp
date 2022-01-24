package com.example.catstestapp.presentation.fragments.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.catstestapp.domain.use_case.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    fun fetchCats() =
        getCatsUseCase().map { it ->
            it.map {
                it.toCatUI()
            }
        }.cachedIn(viewModelScope)
}
