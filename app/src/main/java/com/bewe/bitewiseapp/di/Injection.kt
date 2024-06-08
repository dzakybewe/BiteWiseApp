package com.bewe.bitewiseapp.di

import com.bewe.bitewiseapp.data.remote.api.ApiConfig
import com.bewe.bitewiseapp.data.repository.DataRepository

object Injection {
    fun provideRepository(): DataRepository {
        val apiService = ApiConfig.getApiService()

        return DataRepository.getInstance(apiService)
    }
}