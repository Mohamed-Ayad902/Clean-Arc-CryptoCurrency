package com.example.cleancryptocurrency.presentation.coin_details

import com.example.cleancryptocurrency.domain.models.CoinDetails

data class CoinDetailsUiState(
    val isLoading: Boolean = false,
    val coin: CoinDetails? = null,
    val error: String = ""
)
