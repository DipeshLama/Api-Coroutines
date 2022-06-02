package com.example.apicoroutines.feature.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.model.request.FavouriteRequest
import com.example.apicoroutines.feature.shared.repository.FavouriteRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: FavouriteRepository) :
    ViewModel() {

    fun addToFavourite(token: String, request: FavouriteRequest) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.addToFavourite(token, request)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong"))
        }
    }

    fun getUserFavourite(token : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getUserFavourite(token)))
        } catch (ex: Exception) {
            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong"))
        }
    }
}