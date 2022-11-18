package com.example.cleancryptocurrency.data.source.remote

import com.example.cleancryptocurrency.data.dto.CoinDetailsDto
import com.example.cleancryptocurrency.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("coins/{coin_id}")
    suspend fun getCoinById(@Path("coin_id") coin_id: String): CoinDetailsDto

}