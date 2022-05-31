package com.example.apicoroutines.feature.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentLoginBinding
import com.example.apicoroutines.feature.main.MainActivity
import com.example.apicoroutines.feature.resetPassword.forgotPassword.ForgotPasswordFragment
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.model.request.LoginRequest
import com.example.apicoroutines.feature.shared.model.response.Login
import com.example.apicoroutines.utils.constants.ApiConstants
import com.example.apicoroutines.utils.globalUtils.PreferenceUtils
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.example.apicoroutines.utils.router.Router
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class LoginFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel
        initListener()
        initDialog()
    }

    private fun login() {
        if (checkIsOnline()) {
            loginApiCall(LoginRequest(
                client_id = ApiConstants.client_id,
                client_secret = ApiConstants.client_secret,
                grant_type = ApiConstants.grant_type,
                username = binding.edtLoginName.text.toString(),
                password = binding.edtLoginPassword.text.toString()
            ))
        } else {
            showMessage("Check internet connection")
        }
    }

    private fun loginApiCall(request: LoginRequest) {
        loginViewModel.login(request).observe(
            this) {
            when (it.status) {
                Status.SUCCESS -> onLoginSuccess(it)
                Status.ERROR -> onLoginError(it.message)
                Status.LOADING -> dialog.show()
            }
        }
    }

    private fun onLoginSuccess(it: Resource<Response<Login>>) {
        it.data?.let {
            if (it.isSuccessful) {
                saveToPreference(it.body()?.accessToken,
                    it.body()?.refreshToken,
                    it.body()?.tokenType)

                dialog.dismiss()
                Router.navigateActivity(requireContext(), true, MainActivity::class)
            } else {
                dialog.dismiss()
                showMessage(getError(it.errorBody()?.string()))
            }
        }
    }

    private fun onLoginError(msg: String?) {
        dialog.dismiss()
        showMessage(msg)
    }

    private fun saveToPreference(
        accessToken: String?,
        refreshToken: String?,
        tokenType: String?,
    ) {
        PreferenceUtils.saveToPreference(requireContext(),
            " Bearer $accessToken",
            "Bearer $refreshToken",
            tokenType)
    }

    private fun initDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogin -> login()

            binding.txvSignUp -> findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

            binding.txvForgetPassword -> findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener(this)
        binding.txvSignUp.setOnClickListener(this)
        binding.txvForgetPassword.setOnClickListener(this)
    }
}