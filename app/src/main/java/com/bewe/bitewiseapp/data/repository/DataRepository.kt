package com.bewe.bitewiseapp.data.repository

import com.bewe.bitewiseapp.data.UiState
import com.bewe.bitewiseapp.data.remote.api.ApiService
import com.bewe.bitewiseapp.data.remote.model.ProductsResponse
import retrofit2.HttpException

class DataRepository private constructor(
    private val apiService: ApiService,
) {
    suspend fun getAllProducts(): UiState<ProductsResponse> {
        UiState.Loading
        try {
            val response = apiService.getAllProducts()
            return if (response.products.isEmpty()) {
                UiState.Error("Data Kosong")
            } else {
                UiState.Success(response)
            }
        } catch (e: HttpException) {
            return UiState.Error("HTTP Exception: ${e.message()}")
        }
    }

    companion object {
        fun getInstance(
            apiService: ApiService
        ) = DataRepository(apiService)
    }
}