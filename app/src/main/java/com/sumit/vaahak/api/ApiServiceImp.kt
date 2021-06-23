package com.sumit.vaahak.api

import com.sumit.vaahak.model.ResponseData
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {
    suspend fun getSearchResultData(searchString: String): ResponseData =
        apiService.getSearchResult(searchString = searchString)

}