package com.bewe.bitewiseapp.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bewe.bitewiseapp.common.UiState
import com.bewe.bitewiseapp.data.remote.model.HistoryResponse
import com.bewe.bitewiseapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: UserRepository): ViewModel() {
    private val _result = MutableStateFlow<UiState<HistoryResponse>>(UiState.Loading)
    val result: StateFlow<UiState<HistoryResponse>> get() = _result



    suspend fun history(
    ) {
        viewModelScope.launch {
            _result.value = UiState.Loading
            try {
                val response = repository.history()
                _result.value = response
            } catch (e: Exception) {
                _result.value = UiState.Error("Error on ViewModel: ${e.message}")
            }
        }
    }
}