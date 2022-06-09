package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.databinding.*
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.listener.ProductClickListener
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.feature.shared.viewHolder.*

class HomeScreenAdapter(
    private val homeList: ArrayList<Home>,
    val listener: ProductClickListener,
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val bannerType = 1
    private val categoryType = 2
    private val horizontalType = 3
    private val gridType = 4

    override fun getItemViewType(position: Int) =
        if (position < (homeList.count())) {
            when (homeList[position].viewType) {
                "bannerType" -> bannerType

                "categoryType" -> categoryType

                "horizontal" -> horizontalType

                "grid" -> gridType

                else -> super.getItemViewType(position)
            }
        } else {
            0
        }

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

            horizontalType -> ProductViewHolder(
                AdapterHomeProductBinding.inflate(LayoutInflater.from(
                    parent.context),
                    parent,
                    false), listener)

            else -> GridProductViewHolder(
                AdapterGridProductBinding.inflate(LayoutInflater.from(
                    parent.context),
                    parent,
                    false))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            bannerType -> populateBanner(holder, position)
            categoryType -> populateCategory(holder, position)
            horizontalType -> populateHorizontal(holder, position)
            gridType -> populateGrid(holder, position)
        }
    }

    override fun getItemCount() = homeList.size

    private fun populateBanner(baseVH: BaseViewHolder, position: Int) {
        val holder = baseVH as? BannerViewHolder
        holder?.bind(homeList[position].details ?: emptyList())
    }

    private fun populateCategory(baseVH: BaseViewHolder, position: Int) {
        val holder = baseVH as? HomeCategoryViewHolder
        holder?.bind(homeList[position].categories ?: emptyList())
    }

    private fun populateHorizontal(baseVH: BaseViewHolder, position: Int) {
        val holder = baseVH as? ProductViewHolder
        holder?.bind(homeList[position].sectionDetails?.products ?: emptyList(),
            homeList[position].sectionDetails?.title ?: "")
    }

    private fun populateGrid(baseVH: BaseViewHolder, position: Int) {
        val holder = baseVH as? GridProductViewHolder
        holder?.bind(homeList[position].sectionDetails?.products ?: emptyList(),
            homeList[position].sectionDetails?.title ?: "")
    }
}