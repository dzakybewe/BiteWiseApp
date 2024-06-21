package com.bewe.bitewiseapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bewe.bitewiseapp.common.UiState
import com.bewe.bitewiseapp.data.remote.model.AuthResponse
import com.bewe.bitewiseapp.data.repository.UserRepository
import com.bewe.bitewiseapp.pref.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class MainViewModel (private val repository: UserRepository): ViewModel() {

    private val _result = MutableStateFlow<UiState<AuthResponse>>(UiState.Loading)
    val result: StateFlow<UiState<AuthResponse>> get() = _result

    val session: StateFlow<UserModel?> = repository.getSession().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    val isLoggedIn: StateFlow<Boolean?> = repository.getIsLoggedIn().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )



    private var isAuthenticating = false

    private suspend fun auth(deviceId: String) {
        viewModelScope.launch {
            try {
                val response = repository.auth(deviceId)
                _result.value = response
            } catch (e: Exception) {
                _result.value = UiState.Error("Error on ViewModel: ${e.message}")
            }
        }
    }

    fun initializeSession() {
        viewModelScope.launch {
            repository.getSession().collect { user ->
                if (user.token.isEmpty() && user.deviceId.isEmpty() && !isAuthenticating) {
                    isAuthenticating = true
                    val newDeviceId = UUID.randomUUID().toString()
                    auth(newDeviceId)
                }
            }
        }
    }



}