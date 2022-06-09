package com.example.apicoroutines.feature.checkout

import androidx.lifecycle.ViewModel
import com.example.apicoroutines.feature.shared.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(val repository: CartRepository) : ViewModel() {

}