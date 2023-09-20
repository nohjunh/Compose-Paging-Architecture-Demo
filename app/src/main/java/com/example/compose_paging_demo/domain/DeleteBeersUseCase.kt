package com.example.compose_paging_demo.domain

import com.example.compose_paging_demo.domain.repository.BeerRepository
import javax.inject.Inject

class DeleteBeersUseCase @Inject constructor(
    private val beerRepository: BeerRepository
) {
    suspend operator fun invoke() = beerRepository.deleteAllBeers()

}
