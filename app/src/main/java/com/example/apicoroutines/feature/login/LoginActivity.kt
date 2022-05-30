package com.example.apicoroutines.feature.login

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.ActivityLoginBinding
import com.example.apicoroutines.feature.home.MainActivity
import com.example.apicoroutines.feature.shared.base.BaseActivity
import com.example.apicoroutines.feature.shared.model.request.LoginRequest
import com.example.apicoroutines.feature.shared.model.response.Login
import com.example.apicoroutines.utils.constants.ApiConstants
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import com.example.apicoroutines.utils.router.Router
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var dialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel
        initListener()
        initDialog()
    }

    private fun login() {
        val loginRequest = LoginRequest(
            client_id = ApiConstants.client_id,
            client_secret = ApiConstants.client_secret,
            grant_type = ApiConstants.grant_type,
            username = binding.edtLoginName.text.toString(),
            password = binding.edtLoginPassword.text.toString()
        )

        loginViewModel.login(loginRequest).observe(
            this) {
            when(it.status){
                Status.SUCCESS -> onLoginSuccess(it)
                Status.ERROR -> onLoginError(it.message)
                Status.LOADING -> dialog.show()
            }
        }
    }

    private fun onLoginError(msg: String?) {
        showMessage(msg)
    }

    private fun onLoginSuccess(it: Resource<Response<Login>>) {
        it.data?.let {
            if(it.isSuccessful){
                Router.navigateActivity(this, true,MainActivity::class)
            }else{
                val error = it.errorBody()?.string().let { errorResponse ->
                    getError(errorResponse)
                }
                dialog.dismiss()
                showMessage(error)
            }
        }
    }

    private fun initDialog (){
        dialog = Dialog(this)
        dialog.setContentView(R.layout.loading_dialog)
        dialog.setCancelable(false)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogin -> {
                login()
            }
        }
    }

    private fun initListener (){
        binding.btnLogin.setOnClickListener(this)
    }
}