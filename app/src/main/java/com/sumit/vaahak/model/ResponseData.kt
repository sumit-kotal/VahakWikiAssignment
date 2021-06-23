package com.sumit.vaahak.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseData (

	@Expose @SerializedName("batchcomplete") val batchComplete : Boolean,
	@Expose @SerializedName("query") val query : Query
)