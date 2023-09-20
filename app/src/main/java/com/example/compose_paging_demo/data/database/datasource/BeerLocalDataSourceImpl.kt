package com.example.compose_paging_demo.data.database.datasource

import com.example.compose_paging_demo.data.database.dao.BeerDao
import com.example.compose_paging_demo.data.database.model.BeerEntity

class BeerLocalDataSourceImpl(
    private val beerDao: BeerDao
) : BeerLocalDataSource {
    override suspend fun deleteBeers() = beerDao.deleteBeers()

    override suspend fun upsertBeers(beers: List<BeerEntity>) = beerDao.upsertBeers(beers)

    override suspend fun saveBeersAndDeleteIfRequired(
        beers: List<BeerEntity>,
        shouldDelete: Boolean
    ) {
        beerDao.saveBeersAndDeleteIfRequired(beers, shouldDelete)
    }

    override fun getPagingSource() = beerDao.pagingSource()
}
