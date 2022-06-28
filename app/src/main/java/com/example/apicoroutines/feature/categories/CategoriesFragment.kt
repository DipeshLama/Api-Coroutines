package com.example.apicoroutines.feature.categories

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentCategoriesBinding
import com.example.apicoroutines.feature.shared.adapter.CategoryAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.model.response.Category
import com.example.apicoroutines.utils.helper.hideProgress
import com.example.apicoroutines.utils.helper.showProgress
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.ResourceTest
import com.example.apicoroutines.utils.resource.Status
import com.google.android.gms.common.api.internal.LifecycleFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
        setHasOptionsMenu(true)
        initRecyclerView()

        if (savedInstanceState == null) {
            getCategories()
        } else {
            initList(savedInstanceState.getParcelableArrayList<Category>("state_list") as List<Category>)
        }
    }

//    private fun getCategories() {
//        if (checkIsOnline()) {
//            categoryViewModel.getCategories()
//                .observe(viewLifecycleOwner) {
//                    when (it.status) {
//                        Status.SUCCESS -> onSuccess(it)
//                        Status.ERROR -> onError(it.message)
//                        Status.LOADING -> progressBarVisible()
//                    }
//                }
//        } else {
//            showMessage("Check internet connection")
//        }
//    }

    private fun onSuccess(it: Resource<Response<BaseArrayResponse<Category>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data?.isNotEmpty() == true) {
                progressBarGone()
                initList(it.body()?.data ?: emptyList())
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
        binding.prgCategories.showProgress()
    }

    private fun progressBarGone() {
        binding.prgCategories.hideProgress()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("state_list", categoryList)
    }

    private fun initList(list: List<Category>) {
        categoryList.clear()
        categoryList.addAll(list)
        adapter.notifyItemRangeInserted(0, categoryList.count())
    }

    private fun getCategories() {
        categoryViewModel.categories.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceTest.Loading -> progressBarVisible()

                is ResourceTest.Success -> {
                    progressBarGone()
                    it.data?.let { category ->
                        initList(category.data ?: emptyList())
                    }
                }
                is ResourceTest.Error -> {
                    progressBarGone()
                    showMessage(it.message)
                }
            }
        }
    }
}