package com.example.compose_paging_demo.data.database.di

import com.example.compose_paging_demo.data.database.dao.BeerDao
import com.example.compose_paging_demo.data.database.datasource.BeerLocalDataSource
import com.example.compose_paging_demo.data.database.datasource.BeerLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseDataSourceModule {

    @Provides
    @Singleton
    fun provideBeerLocalDataSource(
        beerDao: BeerDao
    ): BeerLocalDataSource =
        BeerLocalDataSourceImpl(beerDao)

}
