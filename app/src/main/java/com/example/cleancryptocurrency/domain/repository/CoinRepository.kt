package com.example.cleancryptocurrency.domain.repository

import com.example.cleancryptocurrency.common.Resource
import com.example.cleancryptocurrency.data.dto.CoinDetailsDto
import com.example.cleancryptocurrency.domain.models.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(coin_id: String): Flow<Resource<CoinDetailsDto>>

}