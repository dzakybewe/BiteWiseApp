package com.bewe.bitewiseapp.data.remote.api

import com.bewe.bitewiseapp.data.remote.model.ProductsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse
}