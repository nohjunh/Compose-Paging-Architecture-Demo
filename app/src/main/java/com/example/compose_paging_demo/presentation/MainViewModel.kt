package com.example.compose_paging_demo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_paging_demo.domain.DeleteBeersUseCase
import com.example.compose_paging_demo.domain.GetBeersUseCase
import com.example.compose_paging_demo.domain.model.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase,
    private val deleteBeersUseCase: DeleteBeersUseCase
) : ViewModel() {
    val beers: Flow<PagingData<Beer>> = getBeersUseCase()
        .cachedIn(viewModelScope)

    fun deleteAllBeers() {
        viewModelScope.launch {
            deleteBeersUseCase()
        }
    }

}
