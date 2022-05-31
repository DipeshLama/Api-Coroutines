package com.example.apicoroutines.feature.shared.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.LayoutMoreFragmentListBinding
import com.example.apicoroutines.feature.shared.model.response.Options

class OptionsViewHolder(val binding: LayoutMoreFragmentListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(options: Options) {
        binding.txvOptionName.text = options.title
        options.icon?.let { binding.imvOptionIcon.setImageResource(it) }

        if (options.hasOptions == true) {
            binding.imvMoreOptions.setImageResource(R.drawable.ic_pointer)
        }else{
            binding.imvMoreOptions.visibility = View.GONE
        }
    }
}