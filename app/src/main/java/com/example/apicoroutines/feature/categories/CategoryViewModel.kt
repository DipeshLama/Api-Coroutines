package com.example.apicoroutines.feature.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.feature.shared.repository.CategoryRepository
import com.example.apicoroutines.utils.helper.getError
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.ResourceTest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) :
    ViewModel() {

    val categories: MutableLiveData<ResourceTest<BaseArrayResponse<Category>>> = MutableLiveData()

    //    fun getCategories() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(null))
//
//        try {
//            emit(Resource.success(data = repository.getCategories()))
//        } catch (ex: Exception) {
//            emit(Resource.error(data = null, message = ex.message ?: "Something went wrong !"))
//        }
//    }
    fun getCategories() = viewModelScope.launch {
        categories.postValue(ResourceTest.Loading())
        val response = repository.getCategories()
        categories.postValue(handleCategoryResponse(response))
    }

    private fun handleCategoryResponse(response: Response<BaseArrayResponse<Category>>):
            ResourceTest<BaseArrayResponse<Category>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return ResourceTest.Success(it)
            }
        }
        return ResourceTest.Error(data = null, message = response.errorBody()?.string().getError())
    }
}