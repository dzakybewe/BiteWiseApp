package com.bewe.bitewiseapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
) {
	data class Data(

		@field:SerializedName("recommendations")
		val recommendations: List<RecommendationsItem>
	)
}

data class Type(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class RecommendationsItem(

	@field:SerializedName("openHours")
	val openHours: String,

	@field:SerializedName("address")
	val address: String,


	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("type")
	val type: Type,

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem>,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("recommendationScore")
	val recommendationScore: Double,

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

	@field:SerializedName("menus")
	val menus: List<MenusItem>,

	@field:SerializedName("region")
	val region: String,

	@field:SerializedName("openTime")
	val openTime: String,

	@field:SerializedName("priceRange")
	val priceRange: String
)

data class MenusItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("restaurantId")
	val restaurantId: Int
)



data class ReviewsItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("restaurantId")
	val restaurantId: Int,

	@field:SerializedName("content")
	val content: String
)

data class RecommendationBody(
	val typeId: Int,
	val regionId: Int
)
