package com.example.apicoroutines.feature.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.ActivityMainBinding
import com.example.apicoroutines.feature.shared.base.BaseActivity
import com.example.apicoroutines.feature.shared.adapter.HomeScreenAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.feature.shared.model.response.Details
import com.example.apicoroutines.feature.shared.model.response.Home
import com.example.apicoroutines.feature.shared.model.response.Product
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeScreenAdapter: HomeScreenAdapter
    private  var list= arrayListOf<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel
        setUpRecyclerView()
        getHomeScreenData()
    }

    private fun getHomeScreenData() {
        if (checkIsOnline()) {
            getHomeScreenDataFromApi()
        } else {
            getHomeScreenDataFromDb()
        }
    }

    private fun getHomeScreenDataFromApi() {
        mainViewModel.getHomeScreenData()
            .observe(this) {
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
        mainViewModel.saveHomeScreenDataToDb(list)
    }

    private fun getHomeScreenDataFromDb() {
        mainViewModel.getHomeScreenDataFromRoom()
            .observe(this) {
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
        for (home in list){
            when (home.title) {
                "Banner" -> {
                    home.details?.let { this.list.add(it) }
                }
                "Products Collection" -> {
                    home.sectionDetails?.title?.let { this.list.add(it) }
                    home.sectionDetails?.products?.let { this.list.add(it)}
                }
                "Category" -> {
                    home.categories?.let { this.list.add(it) }
                }
            }
        }

        homeScreenAdapter.notifyDataSetChanged()
    }
}