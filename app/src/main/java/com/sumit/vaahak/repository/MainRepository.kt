package com.sumit.vaahak.repository


import com.sumit.vaahak.api.ApiServiceImp
import com.sumit.vaahak.model.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getSearchData(searchString: String): Flow<ResponseData> = flow {
        val response = apiServiceImp.getSearchResultData(searchString)
        emit(response)
    }.flowOn(Dispatchers.IO).conflate()

}