package com.example.apicoroutines.feature.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentSignUpBinding
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.SignUpRequest
import com.example.apicoroutines.feature.shared.model.response.SignUp
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class SignUpFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val signUpRequest = SignUpRequest()

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
        binding.signUp = signUpRequest
        initDialog()
        initListener()
    }


    private fun initListener() {
        binding.btnSignUp.setOnClickListener(this)
    }

    private fun signUp() {
        if (checkIsOnline()) {
            signUpCall()
        } else {
            showMessage("Check internet connection")
        }
    }

    private fun signUpCall() {
        Toast.makeText(requireContext(), "${signUpRequest.password}", Toast.LENGTH_SHORT).show()
        if (isValid()) {
            signUpViewModel.signUp(signUpRequest)
                .observe(this) {
                    when (it.status) {
                        Status.SUCCESS -> onSignUpSuccess(it)
                        Status.ERROR -> onSignUpError(it.message)
                        Status.LOADING -> dialog.show()
                    }
                }
        }
    }

    private fun onSignUpSuccess(it: Resource<Response<BaseResponse<SignUp>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                showMessage("Sign up successful")
                dialog.dismiss()
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

    private fun isValid(): Boolean {
        when {
            binding.edtSignUpFirstName.text?.isEmpty() == true -> {
                setError(binding.edtSignUpFirstName, "First name is required")
                return false
            }

            binding.edtSignUpLastName.text?.isEmpty() == true -> {
                setError(binding.edtSignUpLastName, "Last name is required")
                return false
            }
            binding.edtSignUpEmail.text?.isEmpty() == true -> {
                setError(binding.edtSignUpEmail, "Email is required")
                return false
            }

            binding.edtSignUpPhoneNumber.text?.isEmpty() == true -> {
                setError(binding.edtSignUpPhoneNumber, "Phone number is required")
                return false
            }

            binding.edtSignUpPassword.text?.isEmpty() == true -> {
                setError(binding.edtSignUpPassword, "Password is required")
                return false
            }
        }
        return true
    }
}