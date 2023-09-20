package com.example.compose_paging_demo.data.database.di

import com.example.compose_paging_demo.data.database.BeerDatabase
import com.example.compose_paging_demo.data.database.dao.BeerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesBeerDao(
        database: BeerDatabase,
    ): BeerDao = database.beerDao()

}
