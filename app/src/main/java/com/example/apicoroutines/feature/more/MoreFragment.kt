package com.example.apicoroutines.feature.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentMoreBinding
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.response.ProfileShow
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class MoreFragment : BaseFragment() {
    private lateinit var binding: FragmentMoreBinding
    private val moreViewModel: MoreViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_more,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moreViewModel
        getProfile()
    }

    private fun getProfile() {
        moreViewModel.profileShow(getAccessToken())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onSuccess(it)
                    Status.ERROR -> showMessage(it.message)
                    Status.LOADING -> visibleProgressBar()
                }
            }
    }

    private fun onSuccess(it: Resource<Response<BaseResponse<ProfileShow>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                goneProgressBar()
                setDataIntoViews(it.body()?.data ?: ProfileShow())
            } else {
                goneProgressBar()
                showMessage(it.errorBody()?.string())
            }
        }
    }

    private fun setDataIntoViews(profile: ProfileShow) {
        binding.txvProfileName.text = "${profile.firstName} ${profile.lastName}"
        binding.txvProfilePhoneNumber.text = profile.mobileNumber
        Glide.with(requireContext()).load(profile.image).into(binding.imvProfile)
    }

    private fun visibleProgressBar() {
        binding.prgMore.visibility = View.VISIBLE
    }

    private fun goneProgressBar() {
        binding.prgMore.visibility = View.GONE
    }
}