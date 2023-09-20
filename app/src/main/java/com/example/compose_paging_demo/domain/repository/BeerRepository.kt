package com.example.compose_paging_demo.domain.repository

import androidx.paging.PagingData
import com.example.compose_paging_demo.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    fun getBeers(): Flow<PagingData<Beer>>
    suspend fun deleteAllBeers()
}
