package com.example.apicoroutines.utils.globalUtils

import android.content.Context
import android.content.SharedPreferences
import com.example.apicoroutines.utils.constants.PreferenceConstants

object PreferenceUtils {
    private fun getPreferences(context: Context): SharedPreferences? {
        return context.getSharedPreferences(PreferenceConstants.preferenceName,
            Context.MODE_PRIVATE)
    }

    fun saveToPreference(
        context: Context,
        accessToken: String?,
        refreshToken: String?,
        tokenType: String?,
    ) {
        val editor = getPreferences(context)?.edit()
        editor?.putString(PreferenceConstants.accessToken,accessToken)
        editor?.putString(PreferenceConstants.refreshToken,refreshToken)
        editor?.putString(PreferenceConstants.tokenType,tokenType)
        editor?.apply()
    }

    private fun getAccessToken (context: Context) =
        getPreferences(context)?.getString(PreferenceConstants.accessToken,"") ?: ""
}