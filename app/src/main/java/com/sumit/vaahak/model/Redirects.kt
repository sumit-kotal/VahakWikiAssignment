package com.sumit.vaahak.model

import com.google.gson.annotations.SerializedName

data class Redirects (

	@SerializedName("index") val index : Int,
	@SerializedName("from") val from : String,
	@SerializedName("to") val to : String
)