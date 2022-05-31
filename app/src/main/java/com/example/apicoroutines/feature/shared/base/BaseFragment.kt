package com.example.apicoroutines.feature.shared.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.apicoroutines.feature.shared.model.response.ErrorResponse
import com.example.apicoroutines.utils.helper.NetworkHelper
import com.google.gson.Gson

abstract class BaseFragment : Fragment() {
    protected fun getError(error: String?): String? {

        val gson = Gson()
        val root = gson.fromJson(error, ErrorResponse::class.java)
        val errorMessages = root?.errors

        return if (!errorMessages.isNullOrEmpty()) {
            errorMessages[0]?.message
        } else
            "Default error"
    }

    protected fun showMessage(msg:String?){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            .show()
    }

    protected fun checkIsOnline() =
        NetworkHelper.isNetworkConnected(requireContext())
}