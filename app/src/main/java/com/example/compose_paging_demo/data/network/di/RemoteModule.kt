package com.example.compose_paging_demo.data.network.di

import com.example.compose_paging_demo.data.network.service.BeerApiService
import com.example.compose_paging_demo.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())


    @Provides
    @Singleton
    fun provideBeerApiService(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): BeerApiService =
        retrofit
            .client(okHttpClient)
            .build()
            .create(BeerApiService::class.java)

}
