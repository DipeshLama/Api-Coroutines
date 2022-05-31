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
import com.example.apicoroutines.feature.shared.adapter.OptionsAdapter
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.response.Options
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
        setOptions()
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
        binding.txvRewardPoint.text = profile.totalLoyaltyPoints.toString()
        Glide.with(requireContext()).load(profile.image).into(binding.imvProfile)
    }

    private fun visibleProgressBar() {
        binding.prgMore.visibility = View.VISIBLE
        binding.moreFragmentContainer.visibility = View.INVISIBLE
    }

    private fun goneProgressBar() {
        binding.prgMore.visibility = View.GONE
        binding.moreFragmentContainer.visibility = View.VISIBLE
    }

    private fun setOptions (){
        binding.ryvOptions.adapter = OptionsAdapter(optionList())
    }

    private fun optionList(): ArrayList<Options> {
        val list = ArrayList<Options>()
        val option1 = Options("Order History", R.drawable.ic_paper_iconly)
        val option2 = Options("Delivery Address", R.drawable.ic_location_iconly)
        val option3 = Options("Available Promo Code", R.drawable.ic_ticket_iconly)
        val option4 = Options("Notification", R.drawable.ic_notification_iconly)
        val option5 = Options("Change Password", R.drawable.ic_lock_iconly)
        val option6 = Options("More", R.drawable.ic_more_square_iconly, true)
        val option7 = Options("Terms & Conditions", R.drawable.ic_terms_iconly, false)
        list.add(option1)
        list.add(option2)
        list.add(option3)
        list.add(option4)
        list.add(option5)
        list.add(option6)
        list.add(option7)
        return list
    }
}