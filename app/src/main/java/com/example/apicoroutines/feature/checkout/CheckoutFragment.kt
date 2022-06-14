package com.example.apicoroutines.feature.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentCheckoutBinding
import com.example.apicoroutines.feature.paymentOptions.PaymentOptionsFragment
import com.example.apicoroutines.feature.shared.adapter.AddressAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.listener.OnAddressSelectedListener
import com.example.apicoroutines.feature.shared.listener.OnDeliveryAddressEdit
import com.example.apicoroutines.feature.shared.listener.PassPaymentMethod
import com.example.apicoroutines.feature.shared.model.response.Address
import com.example.apicoroutines.feature.shared.model.response.Cart
import com.example.apicoroutines.feature.shared.model.response.PaymentOptions
import com.example.apicoroutines.utils.helper.DecimalHelper
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class CheckoutFragment : BaseFragment(),
    View.OnClickListener,
    PassPaymentMethod,
    OnAddressSelectedListener,
    OnDeliveryAddressEdit {

    private lateinit var binding: FragmentCheckoutBinding
    private var selectedPosition: Int = 0
    private val checkOutViewModel: CheckOutViewModel by viewModels()
    private val deliveryAddressList = arrayListOf<Address>()
    private lateinit var addressAdapter: AddressAdapter

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
        checkOutViewModel
        initListener()
        setDeliveryAddress()
        getUserCart()
        getDeliveryAddress()
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
//        binding.cart = data
//        binding.executePendingBindings()

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

    private fun getDeliveryAddress() {
        checkOutViewModel.getDeliveryAddress(getAccessToken())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onGetAddressSuccess(it)
                    Status.ERROR -> onGetAddressError(it.message)
                }
            }
    }

    private fun onGetAddressSuccess(it: Resource<Response<BaseArrayResponse<Address>>>) {
        it.data?.let {
            if (it.isSuccessful) {
                if (it.body()?.data?.isNotEmpty() == true) {
                    initList(it.body()?.data ?: emptyList())
                    checkListSize()
                } else {
                    deliveryAddressList.addAll(emptyList())
                }
            } else {
                onGetAddressError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onGetAddressError(msg: String?) {
        showMessage(msg)
    }

    private fun setDeliveryAddress() {
        addressAdapter = AddressAdapter(deliveryAddressList, this, this)
        binding.ryvDeliveryAddress.apply {
            adapter = addressAdapter
            itemAnimator = null
        }
    }

    private fun checkListSize() {
        if (deliveryAddressList.size == 3) {
            binding.layoutAddAddress.visibility = View.GONE
        } else {
            binding.layoutAddAddress.visibility = View.VISIBLE
        }
    }

    private fun initListener() {
        binding.layoutPaymentMethod.setOnClickListener(this)
        binding.txvAddDeliveryAddress.setOnClickListener(this)
    }

    private fun initList(list: List<Address>) {
        deliveryAddressList.clear()
        deliveryAddressList.addAll(list)
        addressAdapter.notifyItemRangeInserted(0, deliveryAddressList.count())
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.layoutPaymentMethod -> {
                activity?.supportFragmentManager?.let {
                    PaymentOptionsFragment(this,
                        selectedPosition).show(it, "")
                }
            }

            binding.txvAddDeliveryAddress -> navigateToAddDeliveryAddress(0, false)
        }
    }

    override fun passPaymentMethod(paymentMethod: PaymentOptions, position: Int) {
        binding.txvCheckOutPaymentOptions.text = paymentMethod.title
        selectedPosition = position
    }

    override fun onAddressSelected(position: Int) {
        deliveryAddressList.forEach {
            it.isDefault = false
        }
        deliveryAddressList[position].isDefault =
            !deliveryAddressList[position].isDefault!!

        addressAdapter.notifyItemRangeChanged(0, deliveryAddressList.count())
    }

    override fun onDeliveryAddressEdit(addressId: Int?) {
        navigateToAddDeliveryAddress(addressId ?: 0, true)
    }

    private fun navigateToAddDeliveryAddress(addressId: Int, isEdit: Boolean) {
        findNavController().navigate(
            CheckoutFragmentDirections.actionCheckoutFragmentToAddDeliveryAddressFragment(
                addressId, isEdit))
    }
}