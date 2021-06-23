package com.sumit.vaahak.model
import com.google.gson.annotations.SerializedName

data class Pages (

    @SerializedName("pageid") val pageid : Int,
    @SerializedName("ns") val ns : Int,
    @SerializedName("title") val title : String,
    @SerializedName("index") val index : Int,
    @SerializedName("thumbnail") val thumbnail : Thumbnail? = Thumbnail(),
    @SerializedName("terms") val terms : Terms?
)