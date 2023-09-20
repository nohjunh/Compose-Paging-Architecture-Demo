package com.example.compose_paging_demo.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.compose_paging_demo.data.database.BeerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase =
        Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beer-database"
        ).build()

}
