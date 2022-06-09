package com.example.apicoroutines.feature.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentCheckoutBinding
import com.example.apicoroutines.feature.cart.CartViewModel
import com.example.apicoroutines.feature.paymentOptions.PaymentOptionsFragment
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.response.Cart
import com.example.apicoroutines.utils.helper.DecimalHelper
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class CheckoutFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_checkout,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel
        initListener()
        getUserCart()
    }

    private fun getUserCart() {
        cartViewModel.getUserCart(getAccessToken())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onGetUserCartSuccess(it)
                    Status.ERROR -> onGetUserCartError(it.message)
                }
            }
    }

    private fun onGetUserCartSuccess(it: Resource<Response<BaseResponse<Cart>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                setView(it.body()?.data)
            } else {
                onGetUserCartError(it.errorBody()?.string())
            }
        }
    }

    private fun setView(data: Cart?) {
        binding.txvCheckoutItemsTotal.text =
            String.format(getString(R.string.nrs_),
                DecimalHelper.getRoundedOffPriceRs(data?.orderAmount ?: 0))

        binding.txvCheckoutDeliveryCharge.text =
            String.format(getString(R.string.nrs_),
                DecimalHelper.getRoundedOffPriceRs(data?.deliveryCharge ?: 0))

        binding.txvCheckoutOfferDiscount.text =
            String.format(getString(R.string.discount_Ns_),
                DecimalHelper.getRoundedOffPriceRs(data?.discount?.toInt() ?: 0))

        binding.txvCheckoutDeliveryDiscount.text =
            String.format(getString(R.string.discount_Ns_),
                DecimalHelper.getRoundedOffPriceRs(data?.scheme ?: 0))

        binding.txvCheckoutGrandTotal.text =
            String.format(getString(R.string.nrs_),
                DecimalHelper.getRoundedOffPriceRs(data?.subTotal?.toInt() ?: 0))
    }

    private fun onGetUserCartError(msg: String?) {
        showMessage(msg)
    }

    private fun initListener(){
        binding.layoutPaymentMethod.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.layoutPaymentMethod -> {
                activity?.supportFragmentManager?.let { PaymentOptionsFragment().show(it, "") }
            }
        }
    }
}