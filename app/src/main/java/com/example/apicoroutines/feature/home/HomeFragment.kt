package com.example.apicoroutines.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentHomeBinding
import com.example.apicoroutines.feature.shared.adapter.HomeScreenAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeScreenAdapter: HomeScreenAdapter
    private var list = arrayListOf<Any>()

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
        setHasOptionsMenu(true)
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
                filterData(it.body()?.data ?: emptyList())
                saveHomeScreenDataToDb(it.body()?.data ?: emptyList())
                progressBarGone()

            } else {
                val error = it.errorBody()?.string().let { errorResponse ->
                    getError(errorResponse)
                }
                progressBarGone()
                showMessage(error)
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
                filterData(it)
            }
    }

    private fun setUpRecyclerView() {
        homeScreenAdapter =
            HomeScreenAdapter(list)

        binding.ryvMain.adapter = homeScreenAdapter
    }

    private fun progressBarVisible() {
        binding.prgHome.visibility = View.VISIBLE
    }

    private fun progressBarGone() {
        binding.prgHome.visibility = View.GONE
    }

    private fun filterData(list: List<Home>) {
        this.list.clear()
        for (home in list) {
            when (home.title) {
                "Banner" -> {
                    home.details?.let { this.list.add(it) }
                }
                "Products Collection" -> {
                    home.sectionDetails?.title?.let { this.list.add(it) }
                    home.sectionDetails?.products?.let { this.list.add(it) }
                }
                "Category" -> {
                    home.categories?.let { this.list.add(it) }
                }
            }
        }

        homeScreenAdapter.notifyDataSetChanged()
    }
}