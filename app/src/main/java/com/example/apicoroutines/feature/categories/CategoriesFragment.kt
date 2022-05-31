package com.example.apicoroutines.feature.categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentCategoriesBinding
import com.example.apicoroutines.feature.shared.adapter.CategoryAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class CategoriesFragment : BaseFragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var adapter: CategoryAdapter
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val categoryList = arrayListOf<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_categories,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel
        initRecyclerView()
        getCategories()
    }

    private fun getCategories() {
        if (checkIsOnline()) {
            categoryViewModel.getCategories()
                .observe(viewLifecycleOwner) {
                    when (it.status) {
                        Status.SUCCESS -> onSuccess(it)
                        Status.ERROR -> onError(it.message)
                        Status.LOADING -> progressBarVisible()
                    }
                }
        } else {
            showMessage("Check internet connection")
        }
    }

    private fun onSuccess(it: Resource<Response<BaseArrayResponse<Category>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data?.isNotEmpty() == true) {
                progressBarGone()
                categoryList.clear()
                categoryList.addAll(it.body()?.data ?: emptyList())
                adapter.notifyItemRangeInserted(0,categoryList.count())
            } else {
                progressBarGone()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onError(msg: String?) {
        showMessage(msg)
    }

    private fun initRecyclerView() {
        adapter = CategoryAdapter(categoryList)
        binding.ryvCategories.adapter = adapter
    }

    private fun progressBarVisible() {
        binding.prgCategories.visibility = View.VISIBLE
    }

    private fun progressBarGone() {
        binding.prgCategories.visibility = View.GONE
    }
}