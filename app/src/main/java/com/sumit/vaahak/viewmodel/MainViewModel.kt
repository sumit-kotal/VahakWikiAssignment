package com.sumit.vaahak.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumit.vaahak.model.Pages
import com.sumit.vaahak.model.ResponseData
import com.sumit.vaahak.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.collections.set

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    val searchResponse: MutableLiveData<ResponseData> = MutableLiveData()


    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    private val searchChannel = ConflatedBroadcastChannel<String>()

    @FlowPreview
    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    fun getSearchResults(searchString: String) {
        searchChannel.trySend(searchString).isSuccess
        getSearchData()
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getSearchData() {
        viewModelScope.launch {
            searchChannel.asFlow()
                .flatMapLatest { search ->
                    mainRepository.getSearchData(search)
                }.catch { e ->
                    Timber.d("main -- ${e.message}")
                }.collect {
                    searchResponse.value = it
                    Timber.d("Result - ${it}")
                }
        }
    }


}