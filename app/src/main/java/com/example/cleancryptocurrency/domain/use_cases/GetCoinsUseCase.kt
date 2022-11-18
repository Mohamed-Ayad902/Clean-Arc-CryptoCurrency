package com.example.cleancryptocurrency.domain.use_cases

import com.example.cleancryptocurrency.common.Resource
import com.example.cleancryptocurrency.domain.mapper.CoinsListMapper
import com.example.cleancryptocurrency.domain.models.Coin
import com.example.cleancryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val coinsListMapper: CoinsListMapper
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { coinsDto -> coinsListMapper.map(coinsDto) }
            emit(Resource.Success(coins))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.toString()))
        }
    }
}