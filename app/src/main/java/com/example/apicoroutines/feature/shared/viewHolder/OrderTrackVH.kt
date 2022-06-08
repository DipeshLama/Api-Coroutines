package com.example.apicoroutines.feature.shared.viewHolder

import android.content.res.Resources
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.LayoutOrderTrackingBinding
import com.example.apicoroutines.feature.shared.model.response.StatusLog

class OrderTrackVH(
    val binding: LayoutOrderTrackingBinding,
    val status: String,
    val size: Int,
    private val currentPosition : Int
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(statusLog: StatusLog) {
        binding.txvTrackingStatus.text = statusLog.title
        Glide.with(itemView).load(statusLog.iconUrl).into(binding.imvTrackOrderIcon)

        when (adapterPosition) {
            0 -> binding.btmDivider.visibility = View.GONE
            size - 1 -> binding.topDivider.visibility = View.GONE
        }

        if(adapterPosition <= currentPosition){
            fillSolid()
            fillLine()
        }

        if(status == statusLog.status){
            removeTopLine()
        }

        if(adapterPosition != 0){
            binding.txvPendingDate.visibility = View.GONE
        }else{
            binding.txvPendingDate.visibility = View.VISIBLE
            binding.txvPendingDate.text = statusLog.date
        }
    }

    private fun fillSolid(){
        binding.trackOutline.setBackgroundResource(R.drawable.ic_track_solid)
    }
    private fun fillLine (){
        binding.topDivider.setDividerColorResource(R.color.colorPrimary)
        binding.btmDivider.setDividerColorResource(R.color.colorPrimary)
    }

    private fun removeTopLine(){
        binding.topDivider.setDividerColorResource(R.color.divider_color)
    }
}