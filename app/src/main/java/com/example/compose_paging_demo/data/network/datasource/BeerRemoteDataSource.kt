package com.example.compose_paging_demo.data.network.datasource

import com.example.compose_paging_demo.data.network.model.BeerResponse

interface BeerRemoteDataSource {
    suspend fun getBeers(page: Int, pageCount: Int): List<BeerResponse>
}
