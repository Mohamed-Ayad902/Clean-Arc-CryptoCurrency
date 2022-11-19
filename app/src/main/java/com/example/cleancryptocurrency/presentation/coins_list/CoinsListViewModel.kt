package com.example.cleancryptocurrency.presentation.coins_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleancryptocurrency.common.Resource
import com.example.cleancryptocurrency.domain.use_cases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) :
    ViewModel() {

    private val _coinsListUiState = MutableStateFlow(CoinsListUiState())
    val coinsListUiState: StateFlow<CoinsListUiState> = _coinsListUiState

    init {
        getCoinsList()
    }

    private fun getCoinsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCoinsUseCase().collectLatest { response ->
                when (response) {
                    is Resource.Error -> _coinsListUiState.update { it.copy(error = response.message!!) }
                    is Resource.Loading -> _coinsListUiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _coinsListUiState.update { it.copy(coinsList = response.data!!) }
                }
            }
        }
    }

}