package com.example.compose_paging_demo.domain

import androidx.paging.PagingData
import com.example.compose_paging_demo.domain.repository.BeerRepository
import com.example.compose_paging_demo.domain.model.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(
    private val beerRepository: BeerRepository
) {
    operator fun invoke(): Flow<PagingData<Beer>> {
        return beerRepository.getBeers()
    }
}
