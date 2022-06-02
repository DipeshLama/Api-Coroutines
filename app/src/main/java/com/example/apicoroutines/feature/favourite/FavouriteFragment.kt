package com.example.apicoroutines.feature.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentFavouriteBinding
import com.example.apicoroutines.feature.shared.adapter.FavouriteAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.model.response.Favourite
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class FavouriteFragment : BaseFragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private val favViewModel: FavouriteViewModel by viewModels()
    private val favouriteList = arrayListOf<Favourite>()
    private lateinit var adapter : FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_favourite,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favViewModel
        setRecyclerView()
        getUserFavourites()
    }

    private fun getUserFavourites() {
        favViewModel.getUserFavourite(getAccessToken())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onFavouriteSuccess(it)
                    Status.LOADING -> visibleProgressBar()
                    Status.ERROR -> onFavouriteError(it.message)
                }
            }
    }

    private fun onFavouriteError(msg: String?) {
        goneProgressBar()
        showMessage(msg)
    }

    private fun onFavouriteSuccess(it: Resource<Response<BaseArrayResponse<Favourite>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data?.isNotEmpty() == true) {
                goneProgressBar()
                favouriteList.clear()
                favouriteList.addAll(it.body()?.data ?: emptyList())
                adapter.notifyItemRangeInserted(0,favouriteList.count())
            }
        }
    }

    private fun setRecyclerView (){
        adapter = FavouriteAdapter(favouriteList)
        binding.ryvFavourites.adapter = adapter
    }

    private fun visibleProgressBar() {
        binding.prgFavourite.visibility = View.VISIBLE
    }

    private fun goneProgressBar() {
        binding.prgFavourite.visibility = View.GONE
    }
}