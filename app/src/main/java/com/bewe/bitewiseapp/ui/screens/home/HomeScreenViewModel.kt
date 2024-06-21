package com.bewe.bitewiseapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bewe.bitewiseapp.common.UiState
import com.bewe.bitewiseapp.data.remote.model.RecommendationResponse
import com.bewe.bitewiseapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: UserRepository): ViewModel() {
    private val _result = MutableStateFlow<UiState<RecommendationResponse>>(UiState.Loading)
    val result: StateFlow<UiState<RecommendationResponse>> get() = _result

    val typeId: StateFlow<Int?> = repository.getTypeId().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    init {
        viewModelScope.launch {
            recommendation(2)
        }
    }

    suspend fun recommendation(
        regionId: Int,
    ) {
        viewModelScope.launch {
            _result.value = UiState.Loading
            try {
                val response = repository.recommendation(
                    regionId = regionId
                )
                _result.value = response
            } catch (e: Exception) {
                _result.value = UiState.Error("Error on ViewModel: ${e.message}")
            }
        }
    }
}