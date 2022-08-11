package com.example.apicoroutines.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.RemoteViews
import android.widget.Toast
import com.example.apicoroutines.R
import com.example.apicoroutines.feature.shared.model.response.ProfileShow
import com.example.apicoroutines.feature.shared.repository.ProfileRepository
import com.example.apicoroutines.network.RetrofitHelper
import com.example.apicoroutines.utils.constants.ApiConstants
import com.example.apicoroutines.utils.globalUtils.PreferenceUtils
import io.reactivex.Single
import okhttp3.*
import java.io.IOException
import javax.inject.Inject


class ProfileWidget : AppWidgetProvider() {

    @Inject
    lateinit var repository: ProfileRepository
    val apiService = RetrofitHelper.getApiService()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun getProfileData(context: Context) {
    try {
        val request = Request.Builder()
            .url(ApiConstants.profileU)
            .build()

        val okHttpClient = OkHttpClient()
        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val handler = Handler(Looper.getMainLooper()).post {
                    parseResponse(body, context)
                }
            }
        })
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

internal fun parseResponse(body: String?, context: Context) {
    try {
        val views = RemoteViews(context.packageName, R.layout.profile_widget)
    } catch (ex: Exception) {

    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.profile_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}





