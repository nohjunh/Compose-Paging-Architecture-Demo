package com.example.compose_paging_demo.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.compose_paging_demo.data.database.model.BeerEntity

@Dao
interface BeerDao {

    @Upsert
    suspend fun upsertBeers(beers: List<BeerEntity>)

    @Query("SELECT * FROM beer_resource")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM beer_resource")
    suspend fun deleteBeers()

    @Transaction
    suspend fun saveBeersAndDeleteIfRequired(beers: List<BeerEntity>, shouldDelete: Boolean) {
        if (shouldDelete) {
            deleteBeers()
        }
        upsertBeers(beers)
    }
}
