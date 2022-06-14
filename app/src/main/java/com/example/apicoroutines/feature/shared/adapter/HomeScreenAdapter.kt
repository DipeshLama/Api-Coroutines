package com.example.apicoroutines.feature.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.*
import com.example.apicoroutines.feature.shared.base.BaseViewHolder
import com.example.apicoroutines.feature.shared.listener.ProductClickListener
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.feature.shared.viewHolder.*
import com.example.apicoroutines.utils.constants.StringConstants

class HomeScreenAdapter(
    private val homeList: ArrayList<Home>,
    val listener: ProductClickListener,
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val bannerType = 1
    private val categoryType = 2
    private val horizontalType = 3
    private val gridType = 4
    private val adsBannerType = 5
    private val brandType = 6

    override fun getItemViewType(position: Int) =
        if (position < (homeList.count())) {
            when (homeList[position].viewType) {
                StringConstants.bannerType -> bannerType

                StringConstants.categoryType -> categoryType

                StringConstants.horizontal -> horizontalType

                StringConstants.grid -> gridType

                StringConstants.adsBannerType -> adsBannerType

                else -> brandType
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

            gridType -> GridProductViewHolder(
                AdapterGridProductBinding.inflate(LayoutInflater.from(
                    parent.context),
                    parent,
                    false), listener)

            adsBannerType -> AdsBannerVH(
                AdapterAdsBannerBinding.inflate(LayoutInflater.from(
                    parent.context),
                    parent,
                    false))

            else -> RoundProductVH(
                AdapterRoundProductsBinding.inflate(LayoutInflater.from(
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
            adsBannerType -> populateAdsBanner(holder, position)
            brandType -> populateBrand(holder, position)
        }
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.alpha)
    }

    private fun populateAdsBanner(baseVH: BaseViewHolder, position: Int) {
        val holder = baseVH as? AdsBannerVH
        holder?.bind(homeList[position].details ?: emptyList())
    }

    private fun populateBrand(baseVH: BaseViewHolder, position: Int) {
        val holder = baseVH as? RoundProductVH
        holder?.bind(homeList[position].sectionDetails?.tags ?: emptyList(),
            homeList[position].sectionDetails?.title ?: "")
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