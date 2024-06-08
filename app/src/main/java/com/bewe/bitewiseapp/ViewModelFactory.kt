package com.bewe.bitewiseapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bewe.bitewiseapp.data.repository.DataRepository
import com.bewe.bitewiseapp.ui.screens.history.HistoryViewModel

class ViewModelFactory(private val repository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}