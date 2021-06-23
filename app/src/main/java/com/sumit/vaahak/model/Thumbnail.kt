package com.sumit.vaahak.model

import com.google.gson.annotations.SerializedName

data class Thumbnail (

	@SerializedName("source") val source : String = "",
	@SerializedName("width") val width : Int = 0,
	@SerializedName("height") val height : Int = 0
)