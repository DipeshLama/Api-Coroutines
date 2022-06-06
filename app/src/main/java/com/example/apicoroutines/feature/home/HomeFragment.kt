package com.example.apicoroutines.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentHomeBinding
import com.example.apicoroutines.feature.productDetail.ProductDetailFragment
import com.example.apicoroutines.feature.shared.adapter.HomeScreenAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.listener.ProductClickListener
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.utils.constants.ApiConstants
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

@AndroidEntryPoint
class HomeFragment : BaseFragment(), ProductClickListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeScreenAdapter: HomeScreenAdapter
    private var homeList = arrayListOf<Home>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel
        setUpRecyclerView()
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            val home = async {
                getHomeScreenData()
            }
            home.await()

            when {
                checkIsOnline() -> {
                    val cart = async {
                        getUserCart(getAccessToken())
                    }
                    cart.await()
                }
                else -> showMessage("Check internet connection")
            }
        }
    }

    private fun getHomeScreenData() {
        when {
            checkIsOnline() -> getHomeScreenDataFromApi()
            else -> getHomeScreenDataFromDb()
        }
    }

    private fun getHomeScreenDataFromApi() {
        homeViewModel.getHomeScreenData()
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onHomeDataRetrieveSuccess(it)
                    Status.ERROR -> onHomeRetrieveError(it.message)
                    Status.LOADING -> progressBarVisible()
                }
            }
    }

    private fun onHomeDataRetrieveSuccess(it: Resource<Response<BaseArrayResponse<Home>>>) {
        it.data?.let {
            progressBarVisible()
            if (it.isSuccessful && it.body()?.data?.isNotEmpty() == true) {
                setViewType(it.body()?.data ?: emptyList())
                initList(it.body()?.data ?: emptyList())
                saveHomeScreenDataToDb(it.body()?.data ?: emptyList())
                progressBarGone()
            } else {
                progressBarGone()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onHomeRetrieveError(msg: String?) {
        progressBarGone()
        showMessage(msg)
    }

    private fun saveHomeScreenDataToDb(list: List<Home>) {
        homeViewModel.saveHomeScreenDataToDb(list)
    }

    private fun getHomeScreenDataFromDb() {
        homeViewModel.getHomeScreenDataFromRoom()
            .observe(viewLifecycleOwner) {
                setViewType(it)
                initList(it)
            }
    }

    private fun setUpRecyclerView() {
        homeScreenAdapter =
            HomeScreenAdapter(homeList, this)

        binding.ryvMain.apply {
            adapter = homeScreenAdapter
            itemAnimator = null
        }
    }

    private fun setViewType(list: List<Home>) {
        list.forEach { response ->
            if (response.title == "Banner") {
                response.viewType = "bannerType"
            } else if (response.title == "Category") {
                response.viewType = "categoryType"
            } else if (response.sectionDetails?.designType == "horizontal") {
                response.viewType = "horizontal"
            } else if (response.sectionDetails?.designType == "grid") {
                response.viewType = "grid"
            } else {
                response.viewType = "oval"
            }
        }
    }

    private fun progressBarVisible() {
        binding.prgHome.visibility = View.VISIBLE
    }

    private fun progressBarGone() {
        binding.prgHome.visibility = View.GONE
    }

    private fun initList(list: List<Home>) {
        this.homeList.clear()
        this.homeList.addAll(list)
        homeScreenAdapter.notifyItemRangeInserted(0, homeList.count())
    }

    override fun onHomeProductClick(productId: Int) {
        navigateToDetail(productId)
    }

    private fun navigateToDetail(id: Int) {
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment,
            bundleOf(ApiConstants.productId to id))
    }
}