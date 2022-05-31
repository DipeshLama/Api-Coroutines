package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutMoreFragmentListBinding
import com.example.apicoroutines.feature.shared.model.response.Options
import com.example.apicoroutines.feature.shared.viewHolder.OptionsViewHolder

class OptionsAdapter(val list: ArrayList<Options>) : RecyclerView.Adapter<OptionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        return OptionsViewHolder(LayoutMoreFragmentListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}