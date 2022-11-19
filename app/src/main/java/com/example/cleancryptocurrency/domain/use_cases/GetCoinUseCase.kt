package com.example.cleancryptocurrency.domain.use_cases

import com.example.cleancryptocurrency.common.Resource
import com.example.cleancryptocurrency.data.dto.toCoin
import com.example.cleancryptocurrency.domain.models.CoinDetails
import com.example.cleancryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coin_id: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coin_id).toCoin()
            emit(Resource.Success(coin))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.toString()))
        }
    }
}