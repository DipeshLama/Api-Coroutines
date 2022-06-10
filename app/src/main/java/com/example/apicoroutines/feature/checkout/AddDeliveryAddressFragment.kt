package com.example.apicoroutines.feature.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentAddDeliveryAddressBinding
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.AddDeliveryAddress
import com.example.apicoroutines.feature.shared.model.response.Address
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class AddDeliveryAddressFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentAddDeliveryAddressBinding
    private val checkOutViewModel: CheckOutViewModel by viewModels()
    private val args: AddDeliveryAddressFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_add_delivery_address,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkOutViewModel
        initListener()
        initDialog()
        getDeliveryAddressById()
    }

    private fun addDeliveryAddress() {
        val request = AddDeliveryAddress(
            customer = binding.edtCheckOutFullName.text.toString(),
            contactNo = binding.edtCheckoutPhoneNum.text.toString(),
            title = binding.edtCheckoutArea.text.toString(),
            latitude = 27.7020,
            longitude = 85.3074,
            isDefault = true)

        if (isValid()) {
            when {
                args.isEdit -> updateDeliveryAddress(request)
                else -> addDeliveryAddress(request)
            }
        }
    }

    private fun addDeliveryAddress(request: AddDeliveryAddress) {
        checkOutViewModel.addDeliveryAddress(getAccessToken(), request)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onAddDeliveryAddressSuccess(it)
                    Status.ERROR -> onAddDeliveryAddressError(it.message)
                    Status.LOADING -> dialog.show()
                }
            }
    }

    private fun onAddDeliveryAddressSuccess(it: Resource<Response<BaseResponse<Address>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                dialog.dismiss()
                showMessage("Added delivery address")
                findNavController().popBackStack()
            } else {
                onAddDeliveryAddressError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onAddDeliveryAddressError(msg: String?) {
        dialog.dismiss()
        showMessage(msg)
    }

    private fun updateDeliveryAddress(request: AddDeliveryAddress) {
        checkOutViewModel.updateDeliveryAddress(getAccessToken(), args.addressId, request)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onUpdateDeliveryAddressSuccess(it)
                    Status.ERROR -> onUpdateDeliveryAddressError(it.message)
                    Status.LOADING -> dialog.show()
                }
            }
    }

    private fun onUpdateDeliveryAddressSuccess(it: Resource<Response<BaseResponse<Address>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                dialog.dismiss()
                showMessage("Updated delivery address")
                findNavController().popBackStack()
            } else {
                onUpdateDeliveryAddressError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onUpdateDeliveryAddressError(msg: String?) {
        dialog.dismiss()
        showMessage(msg)
    }

    private fun getDeliveryAddressById() {
        if (args.isEdit) {
            checkOutViewModel.getDeliveryAddressById(getAccessToken(), args.addressId)
                .observe(viewLifecycleOwner) {
                    when (it.status) {
                        Status.SUCCESS -> onGetAddressByIdSuccess(it)
                        Status.ERROR -> onGetAddressByIdError(it.message)
                    }
                }
        }
    }

    private fun onGetAddressByIdSuccess(it: Resource<Response<BaseResponse<Address>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                setData(it.body()?.data)
            } else {
                onGetAddressByIdError(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onGetAddressByIdError(msg: String?) {
        showMessage(msg)
    }

    private fun initListener() {
        binding.btnConfirmAddress.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnConfirmAddress -> addDeliveryAddress()
        }
    }

    private fun setData(data: Address?) {
        binding.edtCheckOutFullName.setText(data?.customer)
        binding.edtCheckoutPhoneNum.setText(data?.contactNo)
        binding.edtCheckoutRegion.setText(data?.detail?.provience)
        binding.edtCheckoutCity.setText(data?.detail?.district)
        binding.edtCheckoutArea.setText(data?.title)
        binding.edtCheckoutAddress.setText(data?.detail?.district)
    }

    private fun isValid(): Boolean {
        when {
            binding.edtCheckOutFullName.text?.isEmpty() == true -> {
                binding.edtCheckOutFullName.error = "Full name is required"
                binding.edtCheckOutFullName.requestFocus()
                return false
            }

            binding.edtCheckoutPhoneNum.text?.isEmpty() == true -> {
                binding.edtCheckoutPhoneNum.error = "Phone number is required"
                binding.edtCheckoutPhoneNum.requestFocus()
                return false
            }

            binding.edtCheckoutRegion.text?.isEmpty() == true -> {
                binding.edtCheckoutRegion.error = "Region is required"
                binding.edtCheckoutRegion.requestFocus()
                return false
            }

            binding.edtCheckoutCity.text?.isEmpty() == true -> {
                binding.edtCheckoutCity.error = "City is required"
                binding.edtCheckoutCity.requestFocus()
                return false
            }
            binding.edtCheckoutArea.text?.isEmpty() == true -> {
                binding.edtCheckoutArea.error = "Area is required"
                binding.edtCheckoutArea.requestFocus()
                return false
            }
            binding.edtCheckoutAddress.text?.isEmpty() == true -> {
                binding.edtCheckoutAddress.error = "Address is required"
                binding.edtCheckoutAddress.requestFocus()
                return false
            }
        }
        return true
    }
}
