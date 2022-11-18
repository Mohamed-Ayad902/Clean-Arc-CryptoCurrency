package com.example.cleancryptocurrency.di

import com.example.cleancryptocurrency.common.Constants
import com.example.cleancryptocurrency.data.repository.CoinRepositoryImpl
import com.example.cleancryptocurrency.data.source.remote.CoinApi
import com.example.cleancryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoinsApi(): CoinApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)

    @Singleton
    @Provides
    fun provideCoinsRepository(api: CoinApi): CoinRepository = CoinRepositoryImpl(api)

}