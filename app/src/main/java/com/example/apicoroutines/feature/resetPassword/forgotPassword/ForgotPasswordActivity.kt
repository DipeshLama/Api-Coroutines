package com.example.apicoroutines.feature.resetPassword.forgotPassword

import android.app.Dialog
import android.os.Bundle
import android.view.View
import retrofit2.Response
import com.example.apicoroutines.R
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import com.example.apicoroutines.utils.resource.Status
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.base.BaseActivity
import com.example.apicoroutines.feature.shared.model.response.SignUp
import com.example.apicoroutines.databinding.ActivityForgotPasswordBinding
import com.example.apicoroutines.feature.shared.model.request.ForgotPasswordRequest

@AndroidEntryPoint
class ForgotPasswordActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityForgotPasswordBinding
    private val forgotViewModel: ForgotPasswordViewModel by viewModels()
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        forgotViewModel
        initDialog()
        initListener()
    }

    private fun initListener() {
        binding.btnForgotPasswordContinue.setOnClickListener(this)
    }


    private fun forgotPassword() {
        forgotPasswordCall(
            ForgotPasswordRequest(binding.edtForgotPasswordEmail.text.toString().trim())
        )
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
            } else {
                dialog.dismiss()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onError(msg: String?) {
        showMessage(msg)
    }

    private fun initDialog() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnForgotPasswordContinue -> forgotPassword()
        }
    }
}