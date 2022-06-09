package com.example.apicoroutines.feature.shared.base

import android.widget.Toast
import com.example.apicoroutines.feature.shared.model.response.ErrorResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseBottomSheetFragment : BottomSheetDialogFragment(){
    protected fun getError(error: String?): String? {
        val gson = Gson()
        val root = gson.fromJson(error, ErrorResponse::class.java)
        val errorMessages = root?.errors

        return if (!errorMessages.isNullOrEmpty()) {
            errorMessages[0]?.message
        } else
            "Default error"
    }

    protected fun showMessage(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            .show()
    }
}