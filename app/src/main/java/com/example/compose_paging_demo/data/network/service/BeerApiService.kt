package com.example.compose_paging_demo.data.network.service

import com.example.compose_paging_demo.data.network.model.BeerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApiService {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<BeerResponse>

}
