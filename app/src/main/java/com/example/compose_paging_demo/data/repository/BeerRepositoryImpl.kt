package com.example.compose_paging_demo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.compose_paging_demo.data.mediator.BeerMediator
import com.example.compose_paging_demo.data.database.datasource.BeerLocalDataSource
import com.example.compose_paging_demo.data.database.model.toBeer
import com.example.compose_paging_demo.data.network.datasource.BeerRemoteDataSource
import com.example.compose_paging_demo.domain.model.Beer
import com.example.compose_paging_demo.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val beerLocalDataSource: BeerLocalDataSource,
    private val beerRemoteDataSource: BeerRemoteDataSource
) : BeerRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getBeers(): Flow<PagingData<Beer>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerMediator(
                beerLocalDataSource = beerLocalDataSource,
                beerRemoteDataSource = beerRemoteDataSource
            ),
            pagingSourceFactory = {
                beerLocalDataSource.getPagingSource()
            }
        )

        return pager.flow.map { pagingData ->
            pagingData.map { it.toBeer() }
        }
    }

    override suspend fun deleteAllBeers() {
        beerLocalDataSource.deleteBeers()
    }

}
