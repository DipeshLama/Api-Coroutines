package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.*
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.listener.ProductClickListener
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.feature.shared.model.response.Details
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.feature.shared.viewHolder.*

class HomeScreenAdapter(
    private val list: List<*>,val listener :ProductClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val bannerType = 1
    private val categoryType = 2
    private val productType = 3
    private val gridType = 4
    private val titleType = 5

    override fun getItemViewType(position: Int) =
        if (list[position] is List<*>) {
            val a = list[position] as List<*>
            if (a.isNotEmpty() && a[0] is Details)
                bannerType
            else if (a.isNotEmpty() && a[0] is Category)
                categoryType
            else
                productType
        } else titleType


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            bannerType -> BannerViewHolder(
                AdapterBannerBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false))

            categoryType -> HomeCategoryViewHolder(
                AdapterHomeCategoryBinding.inflate(LayoutInflater.from(
                    parent.context),
                    parent,
                    false))

            titleType -> TitleVH(
                AdaptorTitleBinding.inflate(LayoutInflater.from(
                    parent.context),
                    parent,
                    false))

            else -> ProductViewHolder(
                AdapterHomeProductBinding.inflate(LayoutInflater.from(
                    parent.context),
                    parent,
                    false), listener)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> holder.bind(list[position] as List<Details>)
            is HomeCategoryViewHolder -> holder.bind(list[position] as List<Category>)
            is ProductViewHolder -> holder.bind(list[position] as List<Product>)
            is TitleVH -> holder.bind(list[position] as String)
        }
    }

    override fun getItemCount() = list.size
}