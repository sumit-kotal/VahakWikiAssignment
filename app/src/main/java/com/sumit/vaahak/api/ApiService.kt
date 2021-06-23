package com.sumit.vaahak.api

import com.sumit.vaahak.model.ResponseData
import com.sumit.vaahak.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api.php")
    suspend fun getSearchResult(
        @Query("action") action: String = Constants.action,
        @Query("format") format: String = Constants.format,
        @Query("prop") prop: String = Constants.prop,
        @Query("generator") generator: String = Constants.generator,
        @Query("redirects") redirects: String = Constants.redirects,
        @Query("formatversion") formatVersion: String = Constants.formatversion,
        @Query("piprop") piProp : String = Constants.piprop,
        @Query("pithumbsize") piThumbsSize: String = Constants.pithumbsize,
        @Query("pilimit") piLimit: String = Constants.pilimit,
        @Query("wbptterms") wbpTterms: String = Constants.wbptterms,
        @Query("gpssearch") searchString: String,
        @Query("gpslimit") gpsLimit: String = Constants.gpslimit
    ): ResponseData



}