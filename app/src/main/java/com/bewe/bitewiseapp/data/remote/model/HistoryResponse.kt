package com.bewe.bitewiseapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Restaurant(

	@field:SerializedName("openHours")
	val openHours: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("avgPrice")
	val avgPrice: Double,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("minPrice")
	val minPrice: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("closeTime")
	val closeTime: String,

	@field:SerializedName("typeId")
	val typeId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("maxPrice")
	val maxPrice: Int,

	@field:SerializedName("region")
	val region: String,

	@field:SerializedName("openTime")
	val openTime: String,

	@field:SerializedName("priceRange")
	val priceRange: String
)

data class HistoryItem(

	@field:SerializedName("accessedAt")
	val accessedAt: String,

	@field:SerializedName("restaurant")
	val restaurant: Restaurant,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("restaurantId")
	val restaurantId: Int,

	@field:SerializedName("userId")
	val userId: String
)

data class Data(

	@field:SerializedName("history")
	val history: List<HistoryItem>
)
