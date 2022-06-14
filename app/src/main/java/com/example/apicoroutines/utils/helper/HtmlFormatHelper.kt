package com.example.apicoroutines.utils.helper

import androidx.core.text.HtmlCompat

object HtmlFormatHelper {

    @JvmStatic
    fun convertHtmlFormat(text: String?): String {
        return text?.let { HtmlCompat.fromHtml(it, 0) }.toString()
    }
}