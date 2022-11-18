package com.example.cleancryptocurrency.domain.mapper

import com.example.cleancryptocurrency.data.dto.CoinDto
import com.example.cleancryptocurrency.domain.models.Coin

class CoinsListMapper : Mapper<CoinDto, Coin> {
    override fun map(input: CoinDto): Coin {
        return Coin(
            id = input.id,
            is_active = input.is_active,
            name = input.name,
            rank = input.rank,
            symbol = input.symbol
        )
    }
}