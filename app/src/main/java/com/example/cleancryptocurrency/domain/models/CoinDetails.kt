package com.example.cleancryptocurrency.domain.models

import com.example.cleancryptocurrency.data.dto.*

data class CoinDetails(
    val description: String,
    val id: String,
    val is_active: Boolean,
    val logo: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>,
    val type: String,
)
