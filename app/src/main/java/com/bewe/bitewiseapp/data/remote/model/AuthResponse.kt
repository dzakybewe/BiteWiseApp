package com.bewe.bitewiseapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
) {
	data class Data(

		@field:SerializedName("user")
		val user: User,

		@field:SerializedName("token")
		val token: String
	)
}

data class User(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("birthDate")
	val birthDate: String,

	@field:SerializedName("deviceId")
	val deviceId: String,

	@field:SerializedName("username")
	val username: String
)

data class AuthBody(
	val deviceId: String
)