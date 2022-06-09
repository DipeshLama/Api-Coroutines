package com.example.apicoroutines.feature.signup

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentSignUpBinding
import com.example.apicoroutines.feature.login.LoginFragment
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.SignUpRequest
import com.example.apicoroutines.feature.shared.model.response.SignUp
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.example.apicoroutines.utils.router.Router
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class SignUpFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sign_up,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
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


    override fun onClick(view: View?) {
        when (view) {
            binding.btnSignUp -> {
                signUp()
            }
        }
    }
}