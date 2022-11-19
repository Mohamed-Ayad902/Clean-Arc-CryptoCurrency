package com.example.cleancryptocurrency.data.dto

import com.example.cleancryptocurrency.data.source.local.CoinEntity
import com.example.cleancryptocurrency.domain.models.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoinEntity() =
    CoinEntity(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )