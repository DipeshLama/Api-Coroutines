package com.example.apicoroutines.feature.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.apicoroutines.feature.shared.repository.ProfileRepository
import com.example.apicoroutines.utils.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    fun profileShow (token : String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try{
                emit(Resource.success(data = repository.profileShow(token)))
            }catch (ex : Exception){
                emit(Resource.error(data = null, message = ex.message ?: "Something went wrong"))
            }
        }
}