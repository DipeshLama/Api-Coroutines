package com.example.apicoroutines.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apicoroutines.feature.shared.repository.HomeRepository

class HomeViewModelFactory (private val repository: HomeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel (repository) as T
    }
}