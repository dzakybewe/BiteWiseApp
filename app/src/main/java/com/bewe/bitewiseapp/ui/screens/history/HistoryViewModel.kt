package com.bewe.bitewiseapp.ui.screens.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bewe.bitewiseapp.data.UiState
import com.bewe.bitewiseapp.data.remote.model.ProductsResponse
import com.bewe.bitewiseapp.data.repository.DataRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: DataRepository): ViewModel() {
    var result by mutableStateOf<UiState<ProductsResponse>>(UiState.Loading)

    fun getAllProducts() {
        viewModelScope.launch {
            try {
                val response = repository.getAllProducts()
                result = response
            } catch (e: Exception) {
                result = UiState.Error("Error on ViewModel: ${e.message}")
            }
        }
    }
}