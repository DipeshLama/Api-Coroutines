package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutFaqListBinding
import com.example.apicoroutines.feature.shared.listener.OnExpandListener
import com.example.apicoroutines.feature.shared.model.response.Faq
import com.example.apicoroutines.feature.shared.viewHolder.FaqViewHolder

class FaqAdapter(val list: ArrayList<Faq>, val listener : OnExpandListener) : RecyclerView.Adapter<FaqViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        return FaqViewHolder(LayoutFaqListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false),listener)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}