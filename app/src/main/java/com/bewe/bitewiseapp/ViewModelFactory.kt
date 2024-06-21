package com.bewe.bitewiseapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bewe.bitewiseapp.data.repository.UserRepository
import com.bewe.bitewiseapp.di.Injection
import com.bewe.bitewiseapp.ui.screens.detail.DetailScreenViewModel
import com.bewe.bitewiseapp.ui.screens.history.HistoryViewModel
import com.bewe.bitewiseapp.ui.screens.home.HomeScreenViewModel
import com.bewe.bitewiseapp.ui.screens.preferences.PreferencesScreenViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(PreferencesScreenViewModel::class.java) -> {
                PreferencesScreenViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) -> {
                HomeScreenViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailScreenViewModel::class.java) -> {
                DetailScreenViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        fun getAuthInstance(context: Context) = ViewModelFactory(Injection.provideUserRepository(context))
    }
}