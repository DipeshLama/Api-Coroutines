package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.LayoutFaqListBinding
import com.example.apicoroutines.feature.shared.listener.OnExpandListener
import com.example.apicoroutines.feature.shared.model.response.Faq
import com.example.apicoroutines.feature.shared.viewHolder.FaqViewHolder

class FaqAdapter(val listener: OnExpandListener) :
    RecyclerView.Adapter<FaqViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Faq>() {
        override fun areItemsTheSame(oldItem: Faq, newItem: Faq): Boolean {
            return oldItem.isExpanded == newItem.isExpanded
        }

        override fun areContentsTheSame(oldItem: Faq, newItem: Faq): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        return FaqViewHolder(LayoutFaqListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false), listener, differ.currentList.size)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size
}