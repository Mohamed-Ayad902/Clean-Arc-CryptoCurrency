package com.example.cleancryptocurrency.presentation.coin_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleancryptocurrency.common.Resource
import com.example.cleancryptocurrency.domain.use_cases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(private val getCoinDetailsUseCase: GetCoinUseCase) :
    ViewModel() {

    private val _coinUiState = MutableStateFlow(CoinDetailsUiState())
    val coinUiState: StateFlow<CoinDetailsUiState> = _coinUiState

    fun getCoinDetails(coin_id: String) {
        viewModelScope.launch {
            getCoinDetailsUseCase(coin_id).collectLatest {response->
                when(response){
                    is Resource.Error -> _coinUiState.update { it.copy(error = response.message!!) }
                    is Resource.Loading -> _coinUiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> _coinUiState.update { it.copy(coin = response.data!!) }
                }
            }
        }
    }

}