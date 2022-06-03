package com.example.apicoroutines.feature.resetPassword.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Response
import com.example.apicoroutines.R
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.example.apicoroutines.utils.resource.Status
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.response.SignUp
import com.example.apicoroutines.databinding.FragmentForgotPasswordBinding
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.model.request.ForgotPasswordRequest

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentForgotPasswordBinding
    private val forgotViewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_forgot_password,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forgotViewModel
        initDialog()
        initListener()
    }

    private fun initListener() {
        binding.btnForgotPasswordContinue.setOnClickListener(this)
    }

    private fun forgotPassword() {
        when {
            checkIsOnline() -> forgotPasswordCall(
                ForgotPasswordRequest(binding.edtForgotPasswordEmail.text.toString().trim()))

            else -> showMessage("check internet connection")
        }
    }

    private fun forgotPasswordCall(request: ForgotPasswordRequest) {
        forgotViewModel.forgotPassword(request)
            .observe(this) {
                when (it.status) {
                    Status.LOADING -> dialog.show()
                    Status.SUCCESS -> onSuccess(it)
                    Status.ERROR -> onError(it.message)
                }
            }
    }

    private fun onSuccess(it: Resource<Response<BaseResponse<SignUp>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                showMessage("Password reset  success")
                dialog.dismiss()
            } else {
                dialog.dismiss()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onError(msg: String?) {
        showMessage(msg)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnForgotPasswordContinue -> forgotPassword()
        }
    }
}