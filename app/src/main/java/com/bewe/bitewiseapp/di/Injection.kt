package com.bewe.bitewiseapp.di

import android.content.Context
import com.bewe.bitewiseapp.data.remote.api.ApiConfig
import com.bewe.bitewiseapp.data.repository.UserRepository
import com.bewe.bitewiseapp.pref.UserPreference
import com.bewe.bitewiseapp.pref.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
//        val user = runBlocking { pref.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token)
        val apiService = ApiConfig.getApiService()

        return UserRepository.getInstance(apiService, pref)
    }

}