package com.example.apicoroutines.feature.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentOrderTrackingBinding
import com.example.apicoroutines.feature.main.MainActivity
import com.example.apicoroutines.feature.shared.adapter.OrderTrackAdapter
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.response.OrderTracking
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class OrderTrackingFragment : BaseFragment() {
    private lateinit var binding: FragmentOrderTrackingBinding
    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_order_tracking,
                container,
                false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderViewModel
        val id = arguments?.getInt("orderId")
        getOrderById(id ?: 0)
    }

    private fun getOrderById(orderId: Int) {
        orderViewModel.getUserOrderById(getAccessToken(), orderId)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onSuccess(it)
                    Status.ERROR -> onError(it.message)
                    Status.LOADING -> {
                        onLoading(true)
                        showLayout(false)
                    }
                }
            }
    }

    private fun onError(msg: String?) {
        showMessage(msg)
        onLoading(false)
        showLayout(false)
    }

    private fun onSuccess(it: Resource<Response<BaseResponse<OrderTracking>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                onLoading(false)
                showLayout(true)
                setRecyclerView(it.body()?.data)
                setViews(it.body()?.data)
            } else {
                onError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun setViews(data: OrderTracking?) {
        binding.txvOrderItemsTotal.text =
            String.format(getString(R.string.nrs_), data?.orderAmount.toString())
        binding.txvOrderDeliveryCharge.text =
            String.format(getString(R.string.nrs_), data?.deliveryCharge.toString())
        binding.txvOrderOfferDiscount.text =
            String.format(getString(R.string.discount_Ns_), data?.discount.toString())
        binding.txvOrderDeliveryDiscount.text =
            String.format(getString(R.string.discount_Ns_), data?.scheme.toString())
        binding.txvOrderGrandTotal.text =
            String.format(getString(R.string.nrs_), data?.subTotal.toString())
    }

    private fun setRecyclerView(data: OrderTracking?) {
        binding.ryvOrderTrackingStatus.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            adapter = data?.let { OrderTrackAdapter(it) }
        }
    }

    private fun onLoading(visible: Boolean) {
        when {
            visible -> binding.prgOrderTracking.visibility = View.VISIBLE
            else -> binding.prgOrderTracking.visibility = View.GONE
        }
    }

    private fun showLayout(visible: Boolean) {
        when {
            visible -> binding.layoutOrderTracking.visibility = View.VISIBLE
            else -> binding.layoutOrderTracking.visibility = View.GONE
        }
    }
}