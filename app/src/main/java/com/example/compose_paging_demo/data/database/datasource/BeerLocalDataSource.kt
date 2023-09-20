package com.example.compose_paging_demo.data.database.datasource

import androidx.paging.PagingSource
import com.example.compose_paging_demo.data.database.model.BeerEntity

interface BeerLocalDataSource {
    suspend fun deleteBeers()
    suspend fun upsertBeers(beers: List<BeerEntity>)
    suspend fun saveBeersAndDeleteIfRequired(beers: List<BeerEntity>, shouldDelete: Boolean)

    fun getPagingSource(): PagingSource<Int, BeerEntity>
}