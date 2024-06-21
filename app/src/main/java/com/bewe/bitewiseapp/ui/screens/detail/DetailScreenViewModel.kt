package com.bewe.bitewiseapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bewe.bitewiseapp.common.UiState
import com.bewe.bitewiseapp.data.remote.model.DetailResponse
import com.bewe.bitewiseapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repository: UserRepository): ViewModel() {
    private val _result = MutableStateFlow<UiState<DetailResponse>>(UiState.Loading)
    val result: StateFlow<UiState<DetailResponse>> get() = _result

    suspend fun detailRestaurant(
        id: Int,
    ) {
        viewModelScope.launch {
            _result.value = UiState.Loading
            try {
                val response = repository.detailRestaurant(id)
                _result.value = response
            } catch (e: Exception) {
                _result.value = UiState.Error("Error on ViewModel: ${e.message}")
            }
        }
    }
}