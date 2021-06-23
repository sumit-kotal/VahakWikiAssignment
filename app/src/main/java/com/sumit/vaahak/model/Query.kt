package com.sumit.vaahak.model

import com.google.gson.annotations.SerializedName

data class Query (

	@SerializedName("redirects") val redirects : List<Redirects>,
	@SerializedName("pages") val pages : List<Pages>
)