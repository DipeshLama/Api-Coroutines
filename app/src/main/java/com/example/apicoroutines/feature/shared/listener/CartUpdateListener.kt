package com.example.apicoroutines.feature.shared.listener

interface CartUpdateListener {
    fun onCartIncrease(position : Int,cartProductId : Int?)
    fun onCartDecrease(position: Int,cartProductId : Int?)
    fun onDelete(position: Int, cartProductId : Int?)
}