package com.example.apicoroutines.utils.helper

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.apicoroutines.R

object GlideUtil {
    @JvmStatic
    @BindingAdapter("glideImage")
    fun ImageView.setGif(url: String?) {
        if (url != null) {
            Glide.with(this).load(url)
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_logo)
                .into(this)
        }
    }
}