package com.example.apicoroutines.feature.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentOrderHistoryBinding
import com.example.apicoroutines.feature.shared.adapter.OrderAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.listener.OrderTrackListener
import com.example.apicoroutines.feature.shared.model.response.Order
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Response

@AndroidEntryPoint
class OrderHistoryFragment : BaseFragment(), OrderTrackListener {
    private lateinit var binding: FragmentOrderHistoryBinding
    private val orderViewModel: OrderViewModel by viewModels()
    private lateinit var orderAdapter: OrderAdapter
    private var orderList = ArrayList<Order>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_order_history,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderViewModel
        setRecyclerView()
        getUserOrder()
    }

    private fun getUserOrder() {
        orderViewModel.getUserOrder(getAccessToken())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onOrderResponseSuccess(it)
                    Status.ERROR -> onOrderResponseError(it.message)
                    Status.LOADING -> onLoading(true)
                }
            }
    }

    private fun onOrderResponseError(msg: String?) {
        onLoading(false)
        showMessage(msg)
    }

    private fun onOrderResponseSuccess(it: Resource<Response<BaseArrayResponse<Order>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data?.isNotEmpty() == true) {
                orderList.clear()
                orderList.addAll(it.body()?.data ?: emptyList())
                orderAdapter.notifyItemRangeInserted(0, orderList.count())
                onLoading(false)
            } else {
                onOrderResponseError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun setRecyclerView() {
        orderAdapter = OrderAdapter(orderList, this)
        binding.ryvOrderHistory.apply {
            adapter = orderAdapter
            itemAnimator = null
        }
    }

    override fun onTrackOrder(position: Int) {
        findNavController().navigate(R.id.action_orderHistoryFragment_to_orderTrackingFragment,
            bundleOf("orderId" to orderList[position].id))
    }

    private fun onLoading(visible: Boolean) {
        when {
            visible -> {
                binding.prgOrderHistory.visibility = View.VISIBLE
                binding.ryvOrderHistory.visibility = View.GONE
            }
            else -> {
                binding.prgOrderHistory.visibility = View.GONE
                binding.ryvOrderHistory.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBottomNavBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        showBottomNavBar()
    }
}