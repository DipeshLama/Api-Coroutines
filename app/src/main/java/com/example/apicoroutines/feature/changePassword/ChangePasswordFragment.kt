package com.example.apicoroutines.feature.changePassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.apicoroutines.R
import com.example.apicoroutines.databinding.FragmentChangePasswordBinding
import com.example.apicoroutines.feature.shared.base.BaseFragment
import com.example.apicoroutines.feature.shared.base.BaseResponse
import com.example.apicoroutines.feature.shared.model.request.ChangePasswordRequest
import com.example.apicoroutines.feature.shared.model.response.ProfileShow
import com.example.apicoroutines.utils.resource.Resource
import com.example.apicoroutines.utils.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentChangePasswordBinding
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_change_password,
                container,
                false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changePasswordViewModel
        initDialog()
        initListener()
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnChangePassword -> changePassword()
        }
    }

    private fun changePassword() {
        if (isFieldValid()) {
            changePasswordCall(ChangePasswordRequest(
                newPassword = binding.edtNewPassword.text.toString(),
                oldPassword = binding.edtOldPassword.text.toString(),
                confirmPassword = binding.edtConfirmPassword.text.toString()
            ))
        }
    }

    private fun changePasswordCall(request: ChangePasswordRequest) {
        changePasswordViewModel.changePassword(request, getAccessToken())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> dialog.show()
                    Status.SUCCESS -> onChangePasswordSuccess(it)
                    Status.ERROR -> onChangePasswordError(it.message)
                }
            }
    }

    private fun onChangePasswordSuccess(it: Resource<Response<BaseResponse<ProfileShow>>>) {
        it.data?.let {
            if (it.isSuccessful && it.body()?.data != null) {
                dialog.dismiss()
                showMessage("Password changed successfully")
            } else {
                onChangePasswordError(it.errorBody()?.string())
            }
        }
    }

    private fun onChangePasswordError(msg: String?) {
        dialog.dismiss()
        showMessage(msg)
    }

    private fun initListener (){
        binding.btnChangePassword.setOnClickListener(this)
    }

    private fun isFieldValid(): Boolean {
        when {
            binding.edtOldPassword.text?.isEmpty() == true -> {
                binding.edtOldPassword.error = "Enter old password"
                binding.edtOldPassword.requestFocus()
                return false
            }

            binding.edtNewPassword.text?.isEmpty() == true -> {
                binding.edtNewPassword.error = "Enter old password"
                binding.edtNewPassword.requestFocus()
                return false
            }

            binding.edtConfirmPassword.text?.isEmpty() == true -> {
                binding.edtConfirmPassword.error = "Enter old password"
                binding.edtConfirmPassword.requestFocus()
                return false
            }

            (binding.edtNewPassword.text.toString() != binding.edtConfirmPassword.text.toString()) -> {
                binding.edtConfirmPassword.error =
                    "New password and confirm password does not match"
                binding.edtConfirmPassword.requestFocus()
                return false
            }
        }
        return true
    }
}