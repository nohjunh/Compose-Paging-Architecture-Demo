package com.example.compose_paging_demo.data.network.di

import com.example.compose_paging_demo.data.network.datasource.BeerRemoteDataSource
import com.example.compose_paging_demo.data.network.datasource.BeerRemoteDataSourceImpl
import com.example.compose_paging_demo.data.network.service.BeerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideBeerRemoteDataSource(beerApi: BeerApiService): BeerRemoteDataSource =
        BeerRemoteDataSourceImpl(beerApi)

}
