package com.example.cleancryptocurrency.data.repository

import com.example.cleancryptocurrency.common.Resource
import com.example.cleancryptocurrency.data.dto.CoinDetailsDto
import com.example.cleancryptocurrency.data.dto.toCoinEntity
import com.example.cleancryptocurrency.data.source.local.CoinDao
import com.example.cleancryptocurrency.data.source.local.toCoin
import com.example.cleancryptocurrency.data.source.remote.CoinApi
import com.example.cleancryptocurrency.domain.models.Coin
import com.example.cleancryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinApi, private val dao: CoinDao) :
    CoinRepository {

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        val coins = dao.getCoins().map { it.toCoin() }
        emit(Resource.Loading(data = coins))

        try {
            val remoteCoins = api.getCoins()
            dao.deleteCoins(remoteCoins.map { it.toCoinEntity() })
            dao.insertCoins(remoteCoins.map { it.toCoinEntity() })
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.toString()))
        }
        val newCoins = dao.getCoins()
        emit(Resource.Success(newCoins.map { it.toCoin() }))
    }

    override suspend fun getCoinById(coin_id: String): CoinDetailsDto {
        return api.getCoinById(coin_id)
    }
}