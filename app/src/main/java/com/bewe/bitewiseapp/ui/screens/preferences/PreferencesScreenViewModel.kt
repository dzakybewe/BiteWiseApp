package com.bewe.bitewiseapp.ui.screens.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bewe.bitewiseapp.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PreferencesScreenViewModel(private val repository: UserRepository): ViewModel() {

    suspend fun setTypeId(typeId: Int) {
        repository.setTypeId(typeId)
    }

    val getTypeId: StateFlow<Int?> = repository.getTypeId().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        repository.setIsLoggedIn(isLoggedIn)
    }






}