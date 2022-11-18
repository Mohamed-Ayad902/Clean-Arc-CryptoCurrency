package com.example.cleancryptocurrency.data.repository

import com.example.cleancryptocurrency.data.dto.CoinDetailsDto
import com.example.cleancryptocurrency.data.dto.CoinDto
import com.example.cleancryptocurrency.data.source.remote.CoinApi
import com.example.cleancryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinApi) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coin_id: String): CoinDetailsDto {
        return api.getCoinById(coin_id)
    }
}