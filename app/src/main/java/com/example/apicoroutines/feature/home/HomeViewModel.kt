package com.example.apicoroutines.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.feature.shared.repository.HomeRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    fun getHomeScreenData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getHomeScreenData()))

        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occurred"))
        }
    }

    fun saveHomeScreenDataToDb(list: List<Home>) {
        viewModelScope.launch {
            repository.saveHomeScreenDataToDb(list)
        }
    }

    fun getHomeScreenDataFromRoom() = repository.getHomeScreenDataFromRoom()
}