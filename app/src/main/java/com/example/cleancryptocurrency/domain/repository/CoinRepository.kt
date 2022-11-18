package com.example.cleancryptocurrency.domain.repository

import com.example.cleancryptocurrency.data.dto.CoinDetailsDto
import com.example.cleancryptocurrency.data.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coin_id: String): CoinDetailsDto

}