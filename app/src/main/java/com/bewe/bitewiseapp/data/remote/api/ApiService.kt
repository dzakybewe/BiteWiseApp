package com.bewe.bitewiseapp.data.remote.api

import com.bewe.bitewiseapp.data.remote.model.AuthBody
import com.bewe.bitewiseapp.data.remote.model.AuthResponse
import com.bewe.bitewiseapp.data.remote.model.DetailResponse
import com.bewe.bitewiseapp.data.remote.model.HistoryResponse
import com.bewe.bitewiseapp.data.remote.model.RecommendationBody
import com.bewe.bitewiseapp.data.remote.model.RecommendationResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("auth")
    @Headers("Content-Type: application/json")
    suspend fun auth(
        @Body
        authBody: AuthBody,
    ) : AuthResponse

    @POST("recommendation/type")
    suspend fun recommendation(
        @HeaderMap
        headers: Map<String, String>,

        @Body
        recommendationBody: RecommendationBody,
    ) : RecommendationResponse


    @GET("restaurant")
    suspend fun detailRestaurant(
        @HeaderMap
        headers: Map<String, String>,

        @Field("id")
        id: Int,
    ) : DetailResponse

    @GET("history")
    suspend fun history(
        @HeaderMap
        headers: Map<String, String>,
    ) : HistoryResponse
}



