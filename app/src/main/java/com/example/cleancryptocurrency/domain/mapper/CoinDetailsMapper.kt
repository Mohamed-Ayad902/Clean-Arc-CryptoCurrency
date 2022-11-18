package com.example.cleancryptocurrency.domain.mapper

import com.example.cleancryptocurrency.data.dto.CoinDetailsDto
import com.example.cleancryptocurrency.domain.models.CoinDetails

class CoinDetailsMapper : Mapper<CoinDetailsDto, CoinDetails> {
    override fun map(input: CoinDetailsDto): CoinDetails {
        return CoinDetails(
            description = input.description,
            id = input.id,
            is_active = input.is_active,
            logo = input.logo,
            name = input.name,
            rank = input.rank,
            symbol = input.symbol,
            tags = input.tags.map { it.name },
            team = input.team,
            type = input.type
        )
    }
}