package com.example.apicoroutines.feature.paymentOptions

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentPaymentOptionsBinding
import com.example.apicoroutines.feature.shared.adapter.PaymentAdapter
import com.example.apicoroutines.feature.shared.base.BaseArrayResponse
import com.example.apicoroutines.feature.shared.base.BaseBottomSheetFragment
import com.example.apicoroutines.feature.shared.listener.OnPaymentMethodSelectListener
import com.example.apicoroutines.feature.shared.listener.PassPaymentMethod
import com.example.apicoroutines.feature.shared.listener.PassSelectedPaymentMethod
import com.example.apicoroutines.feature.shared.model.response.PaymentOptions
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class PaymentOptionsFragment(
    private val passData: PassPaymentMethod,
    private val selectedPosition: Int
) :
    BaseBottomSheetFragment(),
    OnPaymentMethodSelectListener {

    private lateinit var binding: FragmentPaymentOptionsBinding
    private val paymentViewModel: PaymentOptionsViewModel by viewModels()
    private val paymentList = ArrayList<PaymentOptions>()
    private lateinit var paymentAdapter: PaymentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_payment_options,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paymentViewModel
        setSheetHeight()
        setRecyclerView()
        getPaymentMethods()
    }

    private fun setSheetHeight() {
        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheetInternal = d.findViewById<View>(R.id.bottomSheet)
            bottomSheetInternal?.minimumHeight = Resources.getSystem().displayMetrics.heightPixels
        }
    }

    private fun getPaymentMethods() {
        paymentViewModel.getPaymentMethod()
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> onGetPaymentMethodSuccess(it)
                    Status.ERROR -> onGetPaymentMethodError(it.message)
                    Status.LOADING ->{}
                }
            }
    }

    private fun onGetPaymentMethodSuccess(it: Resource<Response<BaseArrayResponse<PaymentOptions>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data?.isNotEmpty() == true) {
                paymentList.clear()
                paymentList.addAll(it.body()?.data ?: emptyList())
                paymentAdapter.notifyItemRangeInserted(0, paymentList.count())

                paymentList[selectedPosition].isSelected = true
                paymentAdapter.notifyItemChanged(selectedPosition)
            } else {
                onGetPaymentMethodError(it.errorBody()?.string())
            }
        }
    }

    private fun onGetPaymentMethodError(msg: String?) {
        showMessage(msg)
    }

    private fun setRecyclerView() {
        paymentAdapter = PaymentAdapter(paymentList, this)
        binding.ryvPaymentOptions.apply {
            adapter = paymentAdapter
            itemAnimator = null
        }
    }

    override fun onPaymentSelect(position: Int) {
        paymentList.forEach {
            it.isSelected = false
        }
        paymentList[position].isSelected = !paymentList[position].isSelected
        paymentAdapter.notifyItemRangeChanged(0, paymentList.count())

        passData.passPaymentMethod(paymentList[position], position)
    }
}