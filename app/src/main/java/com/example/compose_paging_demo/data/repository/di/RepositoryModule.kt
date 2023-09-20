package com.example.compose_paging_demo.data.repository.di

import com.example.compose_paging_demo.domain.repository.BeerRepository
import com.example.compose_paging_demo.data.repository.BeerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindBeerRepository(
        beerRepository: BeerRepositoryImpl
    ): BeerRepository

}
