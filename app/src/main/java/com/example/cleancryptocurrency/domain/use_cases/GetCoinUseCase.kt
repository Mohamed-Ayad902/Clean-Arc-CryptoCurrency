package com.example.cleancryptocurrency.domain.use_cases

import com.example.cleancryptocurrency.common.Resource
import com.example.cleancryptocurrency.domain.mapper.CoinDetailsMapper
import com.example.cleancryptocurrency.domain.models.CoinDetails
import com.example.cleancryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val coinDetailsMapper: CoinDetailsMapper
) {

    operator fun invoke(coin_id: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading())
            val coin = coinDetailsMapper.map(repository.getCoinById(coin_id))
            emit(Resource.Success(coin))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.toString()))
        }
    }
}