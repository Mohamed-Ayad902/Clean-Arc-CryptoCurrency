package com.example.cleancryptocurrency.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleancryptocurrency.domain.models.Coin

@Entity
data class CoinEntity(
    @PrimaryKey val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
)

fun CoinEntity.toCoin() =
    Coin(
        id = id,
        is_active = is_active,
        name = name,
        symbol = symbol,
        rank = rank
    )