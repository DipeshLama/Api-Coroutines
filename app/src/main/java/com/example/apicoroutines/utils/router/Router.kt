package com.example.apicoroutines.utils.router

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

object Router {

    fun navigateActivity (context : Context, finish : Boolean,className : KClass<*>) {
        context.startActivity(Intent(context,className.java))
        if(finish){
            (context as AppCompatActivity).finish()
        }
    }
}