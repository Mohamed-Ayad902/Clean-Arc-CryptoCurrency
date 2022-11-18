package com.example.cleancryptocurrency.presentation.coins_list

import com.example.cleancryptocurrency.domain.models.Coin

data class CoinsListUiState(
    val isLoading: Boolean = false,
    val coinsList: List<Coin> = emptyList(),
    val error: String = "",
)
