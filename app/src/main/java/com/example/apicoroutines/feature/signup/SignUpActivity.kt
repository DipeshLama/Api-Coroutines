package com.example.apicoroutines.feature.signup

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.ActivitySignUpBinding
import com.example.apicoroutines.feature.login.LoginActivity
import com.example.apicoroutines.feature.shared.base.BaseActivity
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.SignUpRequest
import com.example.apicoroutines.feature.shared.model.response.SignUp
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.example.apicoroutines.utils.router.Router
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class SignUpActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel
        initDialog()
        initListener()
    }

    private fun initListener() {
        binding.btnSignUp.setOnClickListener(this)
    }

    private fun signUp() {
        if (checkIsOnline()) {
            signUpCall(SignUpRequest(
                binding.edtSignUpFirstName.text.toString(),
                binding.edtSignUpLastName.text.toString(),
                binding.edtSignUpEmail.text.toString(),
                binding.edtSignUpPhoneNumber.text.toString(),
                binding.edtSignUpPassword.text.toString()
            ))
        } else {
            showMessage("Check internet connection")
        }
    }

    private fun signUpCall(request: SignUpRequest) {
        signUpViewModel.signUp(request)
            .observe(this) {
                when (it.status) {
                    Status.SUCCESS -> onSignUpSuccess(it)
                    Status.ERROR -> onSignUpError(it.message)
                    Status.LOADING -> dialog.show()
                }
            }
    }

    private fun onSignUpSuccess(it: Resource<Response<BaseResponse<SignUp>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                Router.navigateActivity(this, true, LoginActivity::class)
                showMessage("Sign up successful")
            } else {
                dialog.dismiss()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onSignUpError(message: String?) {
        showMessage(message)
    }

    private fun initDialog() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnSignUp -> {
                signUp()
            }
        }
    }
}