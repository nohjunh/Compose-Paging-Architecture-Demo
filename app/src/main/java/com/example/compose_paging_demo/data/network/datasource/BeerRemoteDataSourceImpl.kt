package com.example.compose_paging_demo.data.network.datasource

import com.example.compose_paging_demo.data.network.model.BeerResponse
import com.example.compose_paging_demo.data.network.service.BeerApiService

class BeerRemoteDataSourceImpl(
    private val beerApi: BeerApiService
) : BeerRemoteDataSource {
    override suspend fun getBeers(page: Int, pageCount: Int): List<BeerResponse> =
        beerApi.getBeers(page, pageCount)
}
