package com.bewe.bitewiseapp.data.repository

import android.util.Log
import com.bewe.bitewiseapp.common.UiState
import com.bewe.bitewiseapp.data.remote.api.ApiService
import com.bewe.bitewiseapp.data.remote.model.AuthBody
import com.bewe.bitewiseapp.data.remote.model.AuthResponse
import com.bewe.bitewiseapp.data.remote.model.DetailResponse
import com.bewe.bitewiseapp.data.remote.model.HistoryResponse
import com.bewe.bitewiseapp.data.remote.model.RecommendationBody
import com.bewe.bitewiseapp.data.remote.model.RecommendationResponse
import com.bewe.bitewiseapp.pref.UserModel
import com.bewe.bitewiseapp.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.HttpException

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
) {
    private suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    suspend fun setTypeId(typeId: Int) {
        userPreference.setTypeId(typeId)
    }

    suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        userPreference.setIsLoggedIn(isLoggedIn)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    fun getTypeId(): Flow<Int> {
        return userPreference.getTypeId()
    }

    fun getIsLoggedIn(): Flow<Boolean> {
        return userPreference.getIsLoggedIn()
    }

    suspend fun auth(deviceId: String): UiState<AuthResponse> {
        UiState.Loading
        try {
            val response = apiService.auth(AuthBody(deviceId))
            val user = UserModel(
                deviceId = deviceId,
                token = response.data.token,
            )
            saveSession(user)
            Log.d("response", response.toString())
            return UiState.Success(response)
        } catch (e: HttpException) {
            return UiState.Error("HTTP Exception: ${e.message()}")
        }
    }

    suspend fun recommendation(
        regionId: Int = 1,
    ): UiState<RecommendationResponse> {
        UiState.Loading

        val typeId = getTypeId().first()
        val token = getSession().first().token

        val recommendationHeaders = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token
        )

        try {
            val response = apiService.recommendation(
                recommendationHeaders,
                RecommendationBody(
                    typeId = typeId,
                    regionId = regionId
                )
            )
            Log.d("response", response.toString())
            return UiState.Success(response)
        } catch (e: HttpException) {
            return UiState.Error("HTTP Exception: ${e.message()}")
        }
    }

    suspend fun detailRestaurant(
        id: Int,
    ): UiState<DetailResponse> {
        UiState.Loading

        val token = getSession().first().token

        val detailHeaders = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token
        )

        try {
            val response = apiService.detailRestaurant(
                detailHeaders,
                id
            )
            Log.d("response", response.toString())
            return UiState.Success(response)
        } catch (e: HttpException) {
            return UiState.Error("HTTP Exception: ${e.message()}")
        }
    }

    suspend fun history(): UiState<HistoryResponse> {
        UiState.Loading

        val token = getSession().first().token

        val detailHeaders = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to token
        )

        try {
            val response = apiService.history(detailHeaders)
            Log.d("response", response.toString())
            return UiState.Success(response)
        } catch (e: HttpException) {
            return UiState.Error("HTTP Exception: ${e.message()}")
        }
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
        ) = UserRepository(apiService, userPreference)
    }
}