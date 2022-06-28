package com.example.apicoroutines.utils.helper

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.showProgress(){
    this.visibility = View.VISIBLE
}

fun ProgressBar.hideProgress(){
    this.visibility = View.GONE
}